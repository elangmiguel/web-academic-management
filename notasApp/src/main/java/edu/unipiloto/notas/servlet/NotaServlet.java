package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.NotaDAO;
import edu.unipiloto.notas.dto.patch.NotaPatch;
import edu.unipiloto.notas.dto.request.NotaRequest;
import edu.unipiloto.notas.dto.response.NotaResponse;
import edu.unipiloto.notas.model.Nota;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet que expone operaciones CRUD y PATCH para la entidad Nota.
 * Reutiliza la infraestructura genérica del servlet base.
 */
public class NotaServlet extends GenericCrudServlet<
        Nota,
        NotaRequest,
        NotaResponse,
        NotaPatch,
        NotaDAO> {

    public NotaServlet() {
        super(new NotaDAO());
    }

    @Override
    protected Class<NotaRequest> getRequestClass() {
        return NotaRequest.class;
    }

    @Override
    protected Class<NotaPatch> getPatchClass() {
        return NotaPatch.class;
    }
}
