package edu.unipiloto.notas.dao;

import edu.unipiloto.notas.dao.template.CrudDAO;
import edu.unipiloto.notas.dto.patch.AsignaturaPatch;
import edu.unipiloto.notas.dto.request.AsignaturaRequest;
import edu.unipiloto.notas.dto.response.AsignaturaResponse;
import edu.unipiloto.notas.mapper.AsignaturaMapper;
import edu.unipiloto.notas.mapper.factory.MapperFactory;
import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.Asignatura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para la entidad {@link Asignatura}.
 */
public class AsignaturaDAO extends CrudDAO<
        Asignatura,
        AsignaturaRequest,
        AsignaturaResponse,
        AsignaturaPatch> {

    private static final String TABLE_NAME = "asignatura";
    private static final String SELECT_COLUMNS = "id, docente_id, nombre, intensidad_horaria, created_at, updated_at";
    private static final String INSERT_COLUMNS = "docente_id, nombre, intensidad_horaria";
    private static final String UPDATE_COLUMNS = "docente_id = ?, nombre = ?, intensidad_horaria = ?";

    private final AsignaturaMapper mapper = MapperFactory.asignatura();

    @Override
    protected String tableName() { return TABLE_NAME; }

    @Override
    protected BaseMapper<Asignatura, AsignaturaRequest, AsignaturaResponse, AsignaturaPatch> mapper() {
        return mapper;
    }

    @Override
    protected String selectColumns() { return SELECT_COLUMNS; }

    @Override
    protected Asignatura fromResultSet(ResultSet rs) throws SQLException {
        return mapper.fromResultSet(rs);
    }

    @Override
    protected String insertColumnsSql() { return INSERT_COLUMNS; }

    @Override
    protected void fillInsert(PreparedStatement stmt, Asignatura e) throws SQLException {
        mapper.fillInsert(stmt, e);
    }

    @Override
    protected String updateSetSql() { return UPDATE_COLUMNS; }

    @Override
    protected void fillUpdate(PreparedStatement stmt, Asignatura e) throws SQLException {
        mapper.fillUpdate(stmt, e);
    }

    @Override
    protected void fillPatch(StringBuilder sql, List<Object> params, AsignaturaPatch patch) {
        mapper.fillPatch(sql, params, patch);
    }
}
