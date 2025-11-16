package edu.unipiloto.notas.dao;

import edu.unipiloto.notas.dao.template.CrudDAO;
import edu.unipiloto.notas.dto.patch.EstudiantePatch;
import edu.unipiloto.notas.dto.request.EstudianteRequest;
import edu.unipiloto.notas.dto.response.EstudianteResponse;
import edu.unipiloto.notas.mapper.EstudianteMapper;
import edu.unipiloto.notas.mapper.factory.MapperFactory;
import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.Estudiante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO específico para la entidad {@link Estudiante}.
 *
 * <p>
 * Esta clase extiende {@link CrudDAO} y define las implementaciones concretas de:
 * <ul>
 *     <li>Nombre de la tabla</li>
 *     <li>Columnas SQL para SELECT, INSERT y UPDATE</li>
 *     <li>Construcción de entidades desde ResultSet</li>
 *     <li>Asignación de parámetros en sentencias preparadas</li>
 *     <li>Lógica de construcción dinámica para operaciones PATCH</li>
 * </ul>
 * Permite operaciones CRUD completas y actualizaciones parciales, utilizando
 * {@link EstudianteMapper} (obtenido mediante {@link MapperFactory#estudiante()})
 * para convertir entre entidad y DTOs.
 * </p>
 *
 * <p>
 * Columnas y sentencias definidas:
 * <ul>
 *   <li>Tabla: {@code estudiante}</li>
 *   <li>SELECT: {@code id, nombres, apellidos, documento, correo, ciclo, created_at, updated_at}</li>
 *   <li>INSERT: {@code nombres, apellidos, documento, correo, ciclo}</li>
 *   <li>UPDATE: {@code nombres = ?, apellidos = ?, documento = ?, correo = ?, ciclo = ?}</li>
 * </ul>
 * </p>
 *
 * <p>
 * El mapper implementa {@link BaseMapper} y proporciona:
 * <ul>
 *     <li>Conversión ResultSet → {@link Estudiante}</li>
 *     <li>Conversión {@link EstudianteRequest} → {@link Estudiante}</li>
 *     <li>Conversión {@link Estudiante} → {@link EstudianteResponse}</li>
 *     <li>Aplicación de parches parciales vía {@link EstudiantePatch}</li>
 * </ul>
 * </p>
 *
 * @author A
 * @version 1.0
 */
public class EstudianteDAO extends CrudDAO<
        Estudiante,
        EstudianteRequest,
        EstudianteResponse,
        EstudiantePatch> {

    /** Nombre de la tabla asociada */
    private static final String TABLE_NAME = "estudiante";

    /** Columnas proyectadas en operaciones SELECT */
    private static final String SELECT_COLUMNS =
            "id, nombres, apellidos, documento, correo, ciclo, created_at, updated_at";

    /** Columnas utilizadas en INSERT (excluye campos autogenerados) */
    private static final String INSERT_COLUMNS =
            "nombres, apellidos, documento, correo, ciclo";

    /** Definición de columnas y placeholders usados en UPDATE */
    private static final String UPDATE_COLUMNS =
            "nombres = ?, apellidos = ?, documento = ?, correo = ?, ciclo = ?";

    /** Mapper específico de Estudiante para convertir entre entidad y DTOs */
    private final EstudianteMapper mapper = MapperFactory.estudiante();

    /**
     * Retorna el nombre de la tabla asociada.
     *
     * @return Nombre de la tabla "estudiante".
     */
    @Override
    protected String tableName() {
        return TABLE_NAME;
    }

    /**
     * Retorna el mapper encargado de todas las conversiones Entity ↔ DTO.
     *
     * @return Instancia de {@link EstudianteMapper}.
     */
    @Override
    protected BaseMapper<Estudiante, EstudianteRequest, EstudianteResponse, EstudiantePatch> mapper() {
        return mapper;
    }

    /**
     * Columnas incluidas en las sentencias SELECT.
     *
     * @return Cadena con columnas separadas por coma.
     */
    @Override
    protected String selectColumns() {
        return SELECT_COLUMNS;
    }

    /**
     * Construye una entidad {@link Estudiante} desde un {@link ResultSet}.
     *
     * @param rs ResultSet posicionado en el registro a mapear.
     * @return Instancia de {@link Estudiante}.
     * @throws SQLException si ocurre un error al acceder a los datos.
     */
    @Override
    protected Estudiante fromResultSet(ResultSet rs) throws SQLException {
        return mapper.fromResultSet(rs);
    }

    /**
     * Columnas usadas en la sentencia INSERT.
     *
     * @return Cadena con columnas permitidas para inserción.
     */
    @Override
    protected String insertColumnsSql() {
        return INSERT_COLUMNS;
    }

    /**
     * Asigna parámetros al PreparedStatement de un INSERT,
     * mapeando los valores de la entidad.
     *
     * @param stmt PreparedStatement del INSERT.
     * @param e Entidad {@link Estudiante} a insertar.
     * @throws SQLException si ocurre un error en la asignación.
     */
    @Override
    protected void fillInsert(PreparedStatement stmt, Estudiante e) throws SQLException {
        mapper.fillInsert(stmt, e);
    }

    /**
     * Columnas y placeholders utilizados en la sentencia UPDATE.
     *
     * @return Cadena "col = ?, col = ?, ..."
     */
    @Override
    protected String updateSetSql() {
        return UPDATE_COLUMNS;
    }

    /**
     * Asigna parámetros a un PreparedStatement de UPDATE.
     *
     * @param stmt PreparedStatement del UPDATE.
     * @param e Entidad {@link Estudiante} con los datos actualizados.
     * @throws SQLException si ocurre un error al asignar valores.
     */
    @Override
    protected void fillUpdate(PreparedStatement stmt, Estudiante e) throws SQLException {
        mapper.fillUpdate(stmt, e);
    }

    /**
     * Construye dinámicamente la sentencia SQL (SET parcial) y la lista de parámetros
     * asociados para una operación PATCH, según los valores presentes en el DTO.
     *
     * @param sql     Acumulador de porciones SET.
     * @param params  Lista de parámetros correspondientes a cada campo modificado.
     * @param patch   DTO {@link EstudiantePatch} con los campos parcialmente actualizados.
     */
    @Override
    protected void fillPatch(StringBuilder sql, List<Object> params, EstudiantePatch patch) {
        mapper.fillPatch(sql, params, patch);
    }
}
