package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.AsignaturaDAO;
import edu.unipiloto.notas.dto.patch.AsignaturaPatch;
import edu.unipiloto.notas.dto.request.AsignaturaRequest;
import edu.unipiloto.notas.dto.response.AsignaturaResponse;
import edu.unipiloto.notas.model.Asignatura;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet encargado de gestionar las operaciones CRUD y PATCH
 * relacionadas con la entidad {@link Asignatura}.
 *
 * <p>Extiende {@link GenericCrudServlet} para aprovechar la
 * infraestructura genérica de mapeo, validación y serialización
 * utilizada en el módulo de servlets.</p>
 *
 * <p>Utiliza {@link AsignaturaDAO} para las operaciones de acceso
 * a datos, y los DTO {@link AsignaturaRequest}, {@link AsignaturaResponse}
 * y {@link AsignaturaPatch} para la comunicación con el cliente.</p>
 */
public class AsignaturaServlet extends GenericCrudServlet<
        Asignatura,
        AsignaturaRequest,
        AsignaturaResponse,
        AsignaturaPatch,
        AsignaturaDAO> {

    /**
     * Constructor por defecto.
     * Inicializa el servlet con una instancia de {@link AsignaturaDAO}.
     */
    public AsignaturaServlet() {
        super(new AsignaturaDAO());
    }

    /**
     * Retorna la clase del DTO de solicitud para operaciones de creación
     * y actualización completa (PUT).
     *
     * @return clase de {@link AsignaturaRequest}
     */
    @Override
    protected Class<AsignaturaRequest> getRequestClass() {
        return AsignaturaRequest.class;
    }

    /**
     * Retorna la clase del DTO utilizado para operaciones de actualización parcial (PATCH).
     *
     * @return clase de {@link AsignaturaPatch}
     */
    @Override
    protected Class<AsignaturaPatch> getPatchClass() {
        return AsignaturaPatch.class;
    }
}
