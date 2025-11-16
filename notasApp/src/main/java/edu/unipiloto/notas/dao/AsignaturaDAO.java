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
 * DAO específico para la entidad {@link Asignatura}.
 * 
 * <p>
 * Esta clase extiende {@link CrudDAO} y proporciona las implementaciones
 * concretas
 * de mapeo, columnas SQL y construcción de sentencias para la tabla
 * "asignatura".
 * Permite operaciones CRUD completas, así como actualizaciones parciales
 * (PATCH),
 * utilizando {@link AsignaturaMapper} para convertir entre entidad y DTOs.
 * </p>
 * 
 * <p>
 * Columnas y sentencias definidas:
 * <ul>
 * <li>Tabla: {@code asignatura}</li>
 * <li>Columnas SELECT:
 * {@code id, docente_id, nombre, intensidad_horaria, created_at, updated_at}</li>
 * <li>Columnas INSERT: {@code docente_id, nombre, intensidad_horaria}</li>
 * <li>Columnas UPDATE:
 * {@code docente_id = ?, nombre = ?, intensidad_horaria = ?}</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Utiliza {@link MapperFactory#asignatura()} para obtener un
 * {@link AsignaturaMapper}
 * que implementa {@link BaseMapper} para convertir entre:
 * <ul>
 * <li>{@link Asignatura} (Entity)</li>
 * <li>{@link AsignaturaRequest} (DTO de inserción/actualización completa)</li>
 * <li>{@link AsignaturaResponse} (DTO de respuesta)</li>
 * <li>{@link AsignaturaPatch} (DTO de actualización parcial)</li>
 * </ul>
 * </p>
 * 
 * @author A
 * @version 1.0
 */
public class AsignaturaDAO extends CrudDAO<Asignatura, AsignaturaRequest, AsignaturaResponse, AsignaturaPatch> {

	/** Nombre de la tabla en la base de datos */
	private static final String TABLE_NAME = "asignatura";

	/** Columnas utilizadas en las consultas SELECT */
	private static final String SELECT_COLUMNS = "id, docente_id, nombre, intensidad_horaria, created_at, updated_at";

	/** Columnas utilizadas en la sentencia INSERT */
	private static final String INSERT_COLUMNS = "docente_id, nombre, intensidad_horaria";

	/** Columnas utilizadas en la sentencia UPDATE (SET) */
	private static final String UPDATE_COLUMNS = "docente_id = ?, nombre = ?, intensidad_horaria = ?";

	/** Mapper específico de Asignatura para conversión entre Entity y DTOs */
	private final AsignaturaMapper mapper = MapperFactory.asignatura();

	/**
	 * Retorna el nombre de la tabla asociada.
	 *
	 * @return Nombre de la tabla "asignatura"
	 */
	@Override
	protected String tableName() {
		return TABLE_NAME;
	}

	/**
	 * Retorna el mapper utilizado para conversión entre Entity y DTOs.
	 *
	 * @return Instancia de {@link AsignaturaMapper} implementando
	 *         {@link BaseMapper}
	 */
	@Override
	protected BaseMapper<Asignatura, AsignaturaRequest, AsignaturaResponse, AsignaturaPatch> mapper() {
		return mapper;
	}

	/**
	 * Columnas utilizadas en las sentencias SELECT.
	 *
	 * @return Columnas separadas por coma
	 */
	@Override
	protected String selectColumns() {
		return SELECT_COLUMNS;
	}

	/**
	 * Construye una instancia de {@link Asignatura} a partir de un ResultSet.
	 *
	 * @param rs ResultSet posicionado en la fila a mapear
	 * @return Entidad {@link Asignatura} correspondiente
	 * @throws SQLException si ocurre un error al leer datos
	 */
	@Override
	protected Asignatura fromResultSet(ResultSet rs) throws SQLException {
		return mapper.fromResultSet(rs);
	}

	/**
	 * Columnas utilizadas en la sentencia INSERT.
	 *
	 * @return Columnas separadas por coma para INSERT
	 */
	@Override
	protected String insertColumnsSql() {
		return INSERT_COLUMNS;
	}

	/**
	 * Establece los parámetros de un PreparedStatement para la inserción.
	 *
	 * @param stmt PreparedStatement asociado al INSERT
	 * @param e    Entidad {@link Asignatura} a insertar
	 * @throws SQLException si ocurre un error al asignar parámetros
	 */
	@Override
	protected void fillInsert(PreparedStatement stmt, Asignatura e) throws SQLException {
		mapper.fillInsert(stmt, e);
	}

	/**
	 * Columnas y placeholders utilizados en la sentencia UPDATE (SET).
	 *
	 * @return Cadena con columnas y placeholders
	 */
	@Override
	protected String updateSetSql() {
		return UPDATE_COLUMNS;
	}

	/**
	 * Establece los parámetros de un PreparedStatement para la actualización
	 * completa.
	 *
	 * @param stmt PreparedStatement asociado al UPDATE
	 * @param e    Entidad {@link Asignatura} con los datos actualizados
	 * @throws SQLException si ocurre un error al asignar parámetros
	 */
	@Override
	protected void fillUpdate(PreparedStatement stmt, Asignatura e) throws SQLException {
		mapper.fillUpdate(stmt, e);
	}

	/**
	 * Construye dinámicamente la sentencia SQL y lista de parámetros para un PATCH
	 * parcial.
	 *
	 * @param sql    Acumulador de la sentencia SQL (SET parcial)
	 * @param params Lista de parámetros correspondientes a las columnas a
	 *               actualizar
	 * @param patch  DTO {@link AsignaturaPatch} con los campos parcialmente
	 *               modificados
	 */
	@Override
	protected void fillPatch(StringBuilder sql, List<Object> params, AsignaturaPatch patch) {
		mapper.fillPatch(sql, params, patch);
	}
}
