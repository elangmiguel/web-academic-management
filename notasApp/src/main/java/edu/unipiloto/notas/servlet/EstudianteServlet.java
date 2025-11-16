package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.EstudianteDAO;
import edu.unipiloto.notas.dto.patch.EstudiantePatch;
import edu.unipiloto.notas.dto.request.EstudianteRequest;
import edu.unipiloto.notas.dto.response.EstudianteResponse;
import edu.unipiloto.notas.model.Estudiante;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet encargado de gestionar las operaciones CRUD y PATCH
 * asociadas a la entidad {@link Estudiante}.
 *
 * <p>Extiende {@link GenericCrudServlet} para reutilizar la
 * infraestructura genérica de manejo de solicitudes, mapeo
 * entre DTOs y modelos, validación y serialización JSON.</p>
 *
 * <p>Utiliza {@link EstudianteDAO} para las operaciones de acceso
 * a datos, y los DTO {@link EstudianteRequest},
 * {@link EstudianteResponse} y {@link EstudiantePatch}
 * para la comunicación con el cliente.</p>
 */
public class EstudianteServlet extends GenericCrudServlet<
        Estudiante,
        EstudianteRequest,
        EstudianteResponse,
        EstudiantePatch,
        EstudianteDAO> {

    /**
     * Constructor por defecto.
     * Inicializa el servlet con una instancia de {@link EstudianteDAO}.
     */
    public EstudianteServlet() {
        super(new EstudianteDAO());
    }

    /**
     * Retorna la clase del DTO de solicitud utilizado para
     * operaciones de creación y actualización completa (PUT).
     *
     * @return clase de {@link EstudianteRequest}
     */
    @Override
    protected Class<EstudianteRequest> getRequestClass() {
        return EstudianteRequest.class;
    }

    /**
     * Retorna la clase del DTO utilizado para actualizaciones parciales (PATCH).
     *
     * @return clase de {@link EstudiantePatch}
     */
    @Override
    protected Class<EstudiantePatch> getPatchClass() {
        return EstudiantePatch.class;
    }
}
