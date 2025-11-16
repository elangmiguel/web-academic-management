package edu.unipiloto.notas.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.unipiloto.notas.dao.NotaDAO;
import edu.unipiloto.notas.dto.response.PromedioResponse;

/**
 * Servlet encargado de generar reportes agregados relacionados con notas,
 * específicamente el cálculo de promedios por estudiante y asignatura.
 *
 * <p>Recibe parámetros opcionales de filtrado ({@code ciclo} y {@code curso})
 * a través de la URL, delega la obtención de datos al {@link NotaDAO} y
 * retorna la respuesta en formato JSON.</p>
 *
 * <p>Extiende {@link HttpServlet} y expone únicamente el método GET.</p>
 */
public class ReporteServlet extends HttpServlet {

    /** DAO utilizado para consultar promedios académicos. */
    private final NotaDAO notaDAO = new NotaDAO();

    /**
     * Procesa solicitudes HTTP GET para obtener reportes de promedios.
     *
     * <p>Flujo de operación:</p>
     * <ul>
     *   <li>Lee los parámetros opcionales {@code ciclo} y {@code curso}.</li>
     *   <li>Invoca {@link NotaDAO#getPromedios(Integer, Integer)} para obtener los datos.</li>
     *   <li>Serializa la lista de {@link PromedioResponse} a JSON utilizando Gson.</li>
     *   <li>En caso de error, responde con código 500 y un mensaje JSON.</li>
     * </ul>
     *
     * @param req  objeto {@link HttpServletRequest} con los parámetros de consulta.
     * @param resp objeto {@link HttpServletResponse} para emitir la respuesta JSON.
     * @throws IOException si ocurre un error al escribir la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer ciclo = null;
        Integer curso = null;

        String cicloParam = req.getParameter("ciclo");
        if (cicloParam != null && !cicloParam.isEmpty()) {
            ciclo = Integer.parseInt(cicloParam);
        }

        String cursoParam = req.getParameter("curso");
        if (cursoParam != null && !cursoParam.isEmpty()) {
            curso = Integer.parseInt(cursoParam);
        }

        try {
            List<PromedioResponse> promedios = notaDAO.getPromedios(ciclo, curso);
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(new Gson().toJson(promedios));
        } catch (Exception ex) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\": \"" + ex.getMessage() + "\"}");
        }
    }
}
