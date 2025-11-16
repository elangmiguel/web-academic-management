package edu.unipiloto.notas.dao;

import edu.unipiloto.notas.dao.template.CrudDAO;
import edu.unipiloto.notas.dto.patch.EstudiantePatch;
import edu.unipiloto.notas.dto.request.EstudianteRequest;
import edu.unipiloto.notas.dto.response.EstudianteResponse;
import edu.unipiloto.notas.mapper.EstudianteMapper;
import edu.unipiloto.notas.mapper.factory.MapperFactory;
import edu.unipiloto.notas.model.Estudiante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para la entidad {@link Estudiante}.
 * <p>
 * Proporciona operaciones CRUD básicas y parcheo dinámico (PATCH)
 * utilizando el {@link EstudianteMapper} para la conversión entre
 * entidad y DTOs.
 */
public class EstudianteDAO extends CrudDAO<
        Estudiante,
        EstudianteRequest,
        EstudianteResponse,
        EstudiantePatch> {

    /** Nombre de la tabla en la base de datos */
    private static final String TABLE_NAME = "estudiante";

    /** Columnas a seleccionar en consultas SELECT */
    private static final String SELECT_COLUMNS = "id, nombres, apellidos, documento, correo, ciclo, created_at, updated_at";

    /** Columnas a insertar en la base de datos */
    private static final String INSERT_COLUMNS = "nombres, apellidos, documento, correo, ciclo";

    /** Columnas con placeholders para UPDATE */
    private static final String UPDATE_COLUMNS = "nombres = ?, apellidos = ?, documento = ?, correo = ?, ciclo = ?";

    /** Mapper específico para Estudiante */
    private final EstudianteMapper mapper = MapperFactory.estudiante();

    @Override
    protected String tableName() { return TABLE_NAME; }

    @Override
    protected EstudianteMapper mapper() { return mapper; }

    @Override
    protected String selectColumns() { return SELECT_COLUMNS; }

    @Override
    protected Estudiante fromResultSet(ResultSet rs) throws SQLException {
        return mapper.fromResultSet(rs);
    }

    @Override
    protected String insertColumnsSql() { return INSERT_COLUMNS; }

    @Override
    protected void fillInsert(PreparedStatement stmt, Estudiante e) throws SQLException {
        mapper.fillInsert(stmt, e);
    }

    @Override
    protected String updateSetSql() { return UPDATE_COLUMNS; }

    @Override
    protected void fillUpdate(PreparedStatement stmt, Estudiante e) throws SQLException {
        mapper.fillUpdate(stmt, e);
    }

    @Override
    protected void fillPatch(StringBuilder sql, List<Object> params, EstudiantePatch patch) {
        mapper.fillPatch(sql, params, patch);
    }
}
