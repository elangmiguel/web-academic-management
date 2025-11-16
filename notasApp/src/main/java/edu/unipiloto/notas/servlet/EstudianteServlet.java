package edu.unipiloto.notas.servlet;

import edu.unipiloto.notas.dao.EstudianteDAO;
import edu.unipiloto.notas.dto.patch.EstudiantePatch;
import edu.unipiloto.notas.dto.request.EstudianteRequest;
import edu.unipiloto.notas.dto.response.EstudianteResponse;
import edu.unipiloto.notas.model.Estudiante;
import edu.unipiloto.notas.servlet.template.GenericCrudServlet;

/**
 * Servlet que expone operaciones CRUD y PATCH para la entidad Estudiante.
 * Delegado completamente en GenericCrudServlet.
 */
public class EstudianteServlet extends GenericCrudServlet<
        Estudiante,
        EstudianteRequest,
        EstudianteResponse,
        EstudiantePatch,
        EstudianteDAO> {

    public EstudianteServlet() {
        super(new EstudianteDAO());
    }

    @Override
    protected Class<EstudianteRequest> getRequestClass() {
        return EstudianteRequest.class;
    }

    @Override
    protected Class<EstudiantePatch> getPatchClass() {
        return EstudiantePatch.class;
    }
}
