package edu.unipiloto.notas.dao;

import edu.unipiloto.notas.dao.template.CrudDAO;
import edu.unipiloto.notas.dto.patch.DocentePatch;
import edu.unipiloto.notas.dto.request.DocenteRequest;
import edu.unipiloto.notas.dto.response.DocenteResponse;
import edu.unipiloto.notas.mapper.DocenteMapper;
import edu.unipiloto.notas.mapper.factory.MapperFactory;
import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.Docente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO específico para la entidad {@link Docente}.
 *
 * <p>
 * Esta clase extiende {@link CrudDAO} y proporciona la implementación concreta
 * de las operaciones CRUD para la tabla {@code docente}. Incluye también soporte
 * para actualizaciones parciales (PATCH), delegando la conversión entre entidad
 * y DTOs al {@link DocenteMapper}.
 * </p>
 *
 * <p>
 * Configuración de columnas y sentencias SQL:
 * <ul>
 *   <li>Tabla: {@code docente}</li>
 *   <li>Columnas SELECT:
 *       {@code id, nombres, apellidos, correo, created_at, updated_at}</li>
 *   <li>Columnas INSERT:
 *       {@code nombres, apellidos, correo}</li>
 *   <li>Columnas UPDATE (SET):
 *       {@code nombres = ?, apellidos = ?, correo = ?}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Utiliza {@link MapperFactory#docente()} para obtener un {@link DocenteMapper},
 * el cual implementa {@link BaseMapper} y permite mapear entre:
 * <ul>
 *   <li>{@link Docente} (Entity)</li>
 *   <li>{@link DocenteRequest} (DTO de inserción/actualización completa)</li>
 *   <li>{@link DocenteResponse} (DTO de salida)</li>
 *   <li>{@link DocentePatch} (DTO para actualizaciones parciales)</li>
 * </ul>
 * </p>
 *
 * @author A
 * @version 1.0
 */
public class DocenteDAO extends CrudDAO<
        Docente,
        DocenteRequest,
        DocenteResponse,
        DocentePatch> {

    /** Nombre de la tabla en la base de datos */
    private static final String TABLE_NAME = "docente";

    /** Columnas utilizadas en SELECT */
    private static final String SELECT_COLUMNS =
            "id, nombres, apellidos, correo, created_at, updated_at";

    /** Columnas utilizadas en INSERT */
    private static final String INSERT_COLUMNS =
            "nombres, apellidos, correo";

    /** Expresión SET utilizada en UPDATE */
    private static final String UPDATE_COLUMNS =
            "nombres = ?, apellidos = ?, correo = ?";

    /** Mapper específico para conversión entre Entity y DTOs */
    private final DocenteMapper mapper = MapperFactory.docente();

    /**
     * Retorna el nombre de la tabla asociada.
     *
     * @return Nombre de la tabla "docente"
     */
    @Override
    protected String tableName() {
        return TABLE_NAME;
    }

    /**
     * Retorna el mapper usado para conversión entre Entity y DTOs.
     *
     * @return Instancia de {@link DocenteMapper}
     */
    @Override
    protected BaseMapper<Docente, DocenteRequest, DocenteResponse, DocentePatch> mapper() {
        return mapper;
    }

    /**
     * Columnas utilizadas en SELECT.
     *
     * @return Cadena con columnas separadas por coma
     */
    @Override
    protected String selectColumns() {
        return SELECT_COLUMNS;
    }

    /**
     * Construye una entidad {@link Docente} desde un ResultSet.
     *
     * @param rs ResultSet posicionado en la fila objetivo
     * @return Instancia de {@link Docente}
     * @throws SQLException si ocurre un error de lectura
     */
    @Override
    protected Docente fromResultSet(ResultSet rs) throws SQLException {
        return mapper.fromResultSet(rs);
    }

    /**
     * Columnas utilizadas en la sentencia INSERT.
     *
     * @return Cadena con columnas separadas por coma
     */
    @Override
    protected String insertColumnsSql() {
        return INSERT_COLUMNS;
    }

    /**
     * Asigna los parámetros necesarios para una inserción.
     *
     * @param stmt PreparedStatement asociado al INSERT
     * @param entity Entidad con los valores a insertar
     * @throws SQLException si ocurre un error al asignar parámetros
     */
    @Override
    protected void fillInsert(PreparedStatement stmt, Docente entity) throws SQLException {
        mapper.fillInsert(stmt, entity);
    }

    /**
     * Cadena SET utilizada para actualizaciones completas.
     *
     * @return Cadena con columnas y placeholders
     */
    @Override
    protected String updateSetSql() {
        return UPDATE_COLUMNS;
    }

    /**
     * Asigna los parámetros necesarios para una actualización completa.
     *
     * @param stmt PreparedStatement asociado al UPDATE
     * @param entity Entidad con los valores actualizados
     * @throws SQLException si ocurre un error al asignar parámetros
     */
    @Override
    protected void fillUpdate(PreparedStatement stmt, Docente entity) throws SQLException {
        mapper.fillUpdate(stmt, entity);
    }

    /**
     * Construye dinámicamente la parte SET y los parámetros para un PATCH.
     *
     * @param sql Acumulador SQL parcial
     * @param params Lista de parámetros correspondientes
     * @param patch DTO {@link DocentePatch} con campos parcialmente actualizados
     */
    @Override
    protected void fillPatch(StringBuilder sql, List<Object> params, DocentePatch patch) {
        mapper.fillPatch(sql, params, patch);
    }
}
