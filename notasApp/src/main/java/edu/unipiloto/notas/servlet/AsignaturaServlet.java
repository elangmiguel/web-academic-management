package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.AsignaturaDAO;
import edu.unipiloto.notas.dto.patch.AsignaturaPatch;
import edu.unipiloto.notas.dto.request.AsignaturaRequest;
import edu.unipiloto.notas.dto.response.AsignaturaResponse;
import edu.unipiloto.notas.model.Asignatura;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet que expone operaciones CRUD y PATCH para la entidad Asignatura.
 * Utiliza el DAO AsignaturaDAO y los DTO correspondientes.
 */
public class AsignaturaServlet extends GenericCrudServlet<
        Asignatura,
        AsignaturaRequest,
        AsignaturaResponse,
        AsignaturaPatch,
        AsignaturaDAO> {

    public AsignaturaServlet() {
        super(new AsignaturaDAO());
    }

    @Override
    protected Class<AsignaturaRequest> getRequestClass() {
        return AsignaturaRequest.class;
    }

    @Override
    protected Class<AsignaturaPatch> getPatchClass() {
        return AsignaturaPatch.class;
    }
}
