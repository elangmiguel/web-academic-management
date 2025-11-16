package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.DocenteDAO;
import edu.unipiloto.notas.dto.patch.DocentePatch;
import edu.unipiloto.notas.dto.request.DocenteRequest;
import edu.unipiloto.notas.dto.response.DocenteResponse;
import edu.unipiloto.notas.model.Docente;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet que expone operaciones CRUD y PATCH para la entidad Docente.
 * Implementa la lógica genérica heredada de GenericCrudServlet.
 */
public class DocenteServlet extends GenericCrudServlet<
        Docente,
        DocenteRequest,
        DocenteResponse,
        DocentePatch,
        DocenteDAO> {

    public DocenteServlet() {
        super(new DocenteDAO());
    }

    @Override
    protected Class<DocenteRequest> getRequestClass() {
        return DocenteRequest.class;
    }

    @Override
    protected Class<DocentePatch> getPatchClass() {
        return DocentePatch.class;
    }
}
