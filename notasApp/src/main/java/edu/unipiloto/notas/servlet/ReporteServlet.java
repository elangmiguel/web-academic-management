package edu.unipiloto.notas.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.unipiloto.notas.dao.NotaDAO;
import edu.unipiloto.notas.dto.response.PromedioResponse;

public class ReporteServlet extends HttpServlet {

    private final NotaDAO notaDAO = new NotaDAO();

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
