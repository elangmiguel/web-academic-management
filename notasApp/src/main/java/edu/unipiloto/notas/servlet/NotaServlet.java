package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.NotaDAO;
import edu.unipiloto.notas.dto.patch.NotaPatch;
import edu.unipiloto.notas.dto.request.NotaRequest;
import edu.unipiloto.notas.dto.response.NotaResponse;
import edu.unipiloto.notas.model.Nota;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet encargado de gestionar las operaciones CRUD y PATCH
 * asociadas a la entidad {@link Nota}.
 *
 * <p>Extiende {@link GenericCrudServlet} con el fin de reutilizar
 * la infraestructura genérica de procesamiento de solicitudes,
 * validación, serialización JSON y mapeo entre modelos y DTOs.</p>
 *
 * <p>Utiliza {@link NotaDAO} para las operaciones de acceso a datos y
 * los DTO {@link NotaRequest}, {@link NotaResponse} y {@link NotaPatch}
 * para la interacción estructurada con el cliente.</p>
 */
public class NotaServlet extends GenericCrudServlet<
        Nota,
        NotaRequest,
        NotaResponse,
        NotaPatch,
        NotaDAO> {

    /**
     * Constructor por defecto.
     * Inicializa el servlet con una instancia de {@link NotaDAO}.
     */
    public NotaServlet() {
        super(new NotaDAO());
    }

    /**
     * Retorna la clase del DTO utilizado en operaciones de creación
     * y actualización completa (PUT).
     *
     * @return clase de {@link NotaRequest}
     */
    @Override
    protected Class<NotaRequest> getRequestClass() {
        return NotaRequest.class;
    }

    /**
     * Retorna la clase del DTO utilizado para las operaciones de
     * actualización parcial (PATCH).
     *
     * @return clase de {@link NotaPatch}
     */
    @Override
    protected Class<NotaPatch> getPatchClass() {
        return NotaPatch.class;
    }
}
