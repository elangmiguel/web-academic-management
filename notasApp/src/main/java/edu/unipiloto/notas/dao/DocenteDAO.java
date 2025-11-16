package edu.unipiloto.notas.dao;

import edu.unipiloto.notas.dao.template.CrudDAO;
import edu.unipiloto.notas.dto.patch.DocentePatch;
import edu.unipiloto.notas.dto.request.DocenteRequest;
import edu.unipiloto.notas.dto.response.DocenteResponse;
import edu.unipiloto.notas.mapper.DocenteMapper;
import edu.unipiloto.notas.mapper.factory.MapperFactory;
import edu.unipiloto.notas.model.Docente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para la entidad {@link Docente}.
 */
public class DocenteDAO extends CrudDAO<
        Docente,
        DocenteRequest,
        DocenteResponse,
        DocentePatch> {

    private static final String TABLE_NAME = "docente";
    private static final String SELECT_COLUMNS = "id, nombres, apellidos, correo, created_at, updated_at";
    private static final String INSERT_COLUMNS = "nombres, apellidos, correo";
    private static final String UPDATE_COLUMNS = "nombres = ?, apellidos = ?, correo = ?";

    private final DocenteMapper mapper = MapperFactory.docente();

    @Override
    protected String tableName() { return TABLE_NAME; }

    @Override
    protected DocenteMapper mapper() { return mapper; }

    @Override
    protected String selectColumns() { return SELECT_COLUMNS; }

    @Override
    protected Docente fromResultSet(ResultSet rs) throws SQLException {
        return mapper.fromResultSet(rs);
    }

    @Override
    protected String insertColumnsSql() { return INSERT_COLUMNS; }

    @Override
    protected void fillInsert(PreparedStatement stmt, Docente e) throws SQLException {
        mapper.fillInsert(stmt, e);
    }

    @Override
    protected String updateSetSql() { return UPDATE_COLUMNS; }

    @Override
    protected void fillUpdate(PreparedStatement stmt, Docente e) throws SQLException {
        mapper.fillUpdate(stmt, e);
    }

    @Override
    protected void fillPatch(StringBuilder sql, List<Object> params, DocentePatch patch) {
        mapper.fillPatch(sql, params, patch);
    }
}
