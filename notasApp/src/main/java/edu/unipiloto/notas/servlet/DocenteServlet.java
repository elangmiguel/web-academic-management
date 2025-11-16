package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.DocenteDAO;
import edu.unipiloto.notas.dto.patch.DocentePatch;
import edu.unipiloto.notas.dto.request.DocenteRequest;
import edu.unipiloto.notas.dto.response.DocenteResponse;
import edu.unipiloto.notas.model.Docente;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet encargado de gestionar las operaciones CRUD y PATCH
 * relacionadas con la entidad {@link Docente}.
 *
 * <p>Extiende {@link GenericCrudServlet} para reutilizar la
 * infraestructura genérica de procesamiento de solicitudes,
 * serialización JSON, validación y mapeo entre DTOs y modelos.</p>
 *
 * <p>Utiliza {@link DocenteDAO} para las operaciones de acceso
 * a datos y los DTO {@link DocenteRequest}, {@link DocenteResponse}
 * y {@link DocentePatch} para la comunicación estructurada
 * con el cliente.</p>
 */
public class DocenteServlet extends GenericCrudServlet<
        Docente,
        DocenteRequest,
        DocenteResponse,
        DocentePatch,
        DocenteDAO> {

    /**
     * Constructor por defecto.
     * Inicializa el servlet con una instancia de {@link DocenteDAO}.
     */
    public DocenteServlet() {
        super(new DocenteDAO());
    }

    /**
     * Retorna la clase del DTO utilizado para operaciones de creación
     * y actualización completa (PUT).
     *
     * @return clase del DTO {@link DocenteRequest}
     */
    @Override
    protected Class<DocenteRequest> getRequestClass() {
        return DocenteRequest.class;
    }

    /**
     * Retorna la clase del DTO utilizado para operaciones de actualización
     * parcial mediante PATCH.
     *
     * @return clase del DTO {@link DocentePatch}
     */
    @Override
    protected Class<DocentePatch> getPatchClass() {
        return DocentePatch.class;
    }
}
