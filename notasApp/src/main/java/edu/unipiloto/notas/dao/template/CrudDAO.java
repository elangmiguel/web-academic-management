/**
 * Clase genérica de acceso a datos (DAO) para entidades basadas en {@link BaseModel}.
 * Proporciona operaciones CRUD completas, soporte para inserciones y actualizaciones
 * completas (PUT) y parciales (PATCH), así como mapeo automático entre entidades
 * y DTOs mediante {@link BaseMapper}.
 *
 * <p>
 * Esta clase sirve como plantilla para implementar DAOs específicos de cada entidad.
 * Las subclases deben definir:
 * <ul>
 *   <li>Nombre de la tabla asociada.</li>
 *   <li>Mapper correspondiente para conversión entre Entity y DTOs.</li>
 *   <li>Construcción de sentencias SQL específicas de cada operación.</li>
 *   <li>Mapeo de ResultSet a entidad.</li>
 * </ul>
 * </p>
 *
 * @param <Entity>      Tipo de entidad persistida que extiende {@link BaseModel}.
 * @param <RequestDTO>  DTO utilizado para inserción y actualización completa.
 * @param <ResponseDTO> DTO devuelto en las consultas.
 * @param <PatchDTO>    DTO utilizado para actualizaciones parciales (PATCH).
 * 
 * @author A
 * @version 1.0
 */
package edu.unipiloto.notas.dao.template;

import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.template.BaseModel;
import edu.unipiloto.notas.config.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CrudDAO<Entity extends BaseModel, RequestDTO, ResponseDTO, PatchDTO> {

	/**
	 * Obtiene el nombre de la tabla asociada a la entidad.
	 *
	 * @return Nombre de la tabla en la base de datos.
	 */
	protected abstract String tableName();

	/**
	 * Obtiene el mapper responsable de convertir entre Entity y los DTOs
	 * correspondientes.
	 *
	 * @return Instancia de {@link BaseMapper} asociada a la entidad.
	 */
	protected abstract BaseMapper<Entity, RequestDTO, ResponseDTO, PatchDTO> mapper();

	/**
	 * Columnas que se incluirán en las consultas SELECT.
	 *
	 * @return Cadena con columnas separadas por coma, por defecto "*".
	 */
	protected String selectColumns() {
		return "*";
	}

	/**
	 * Convierte una fila de ResultSet en una instancia de la entidad.
	 *
	 * @param rs ResultSet posicionado en la fila a mapear.
	 * @return Entidad correspondiente a la fila actual.
	 * @throws SQLException Si ocurre un error al leer datos del ResultSet.
	 */
	protected abstract Entity fromResultSet(ResultSet rs) throws SQLException;

	/**
	 * Columnas utilizadas en la sentencia INSERT.
	 *
	 * @return Cadena con columnas separadas por coma, sin incluir VALUES.
	 */
	protected abstract String insertColumnsSql();

	/**
	 * Establece los parámetros de un PreparedStatement para una inserción.
	 *
	 * @param stmt   PreparedStatement asociado al INSERT.
	 * @param entity Entidad que contiene los valores a insertar.
	 * @throws SQLException Si ocurre un error al asignar los parámetros.
	 */
	protected abstract void fillInsert(PreparedStatement stmt, Entity entity) throws SQLException;

	/**
	 * Construye la cláusula SET para una sentencia UPDATE completa.
	 *
	 * @return Cadena con columnas y placeholders separados por coma, sin incluir
	 *         WHERE.
	 */
	protected abstract String updateSetSql();

	/**
	 * Establece los parámetros de un PreparedStatement para una actualización
	 * completa.
	 *
	 * @param stmt   PreparedStatement asociado al UPDATE.
	 * @param entity Entidad con los datos actualizados.
	 * @throws SQLException Si ocurre un error al asignar los parámetros.
	 */
	protected abstract void fillUpdate(PreparedStatement stmt, Entity entity) throws SQLException;

	/**
	 * Construye dinámicamente la sentencia SQL y lista de parámetros para un PATCH
	 * parcial.
	 *
	 * @param sql    Acumulador de la sentencia SQL (SET parcial).
	 * @param params Lista de parámetros que se usarán en PreparedStatement.
	 * @param patch  DTO con los campos que serán modificados.
	 */
	protected abstract void fillPatch(StringBuilder sql, List<Object> params, PatchDTO patch);

	/* ----------------------- LÓGICA CRUD GENÉRICA ----------------------- */

	/**
	 * Obtiene una conexión desde el datasource configurado.
	 *
	 * @return Conexión activa.
	 * @throws Exception Si no es posible obtener la conexión.
	 */
	protected Connection getConnection() throws Exception {
		return ConnectionManager.getDataSource().getConnection();
	}

	/**
	 * Busca un registro por su identificador único.
	 *
	 * @param id Identificador del registro.
	 * @return DTO de respuesta correspondiente al registro o null si no existe.
	 * @throws Exception Si ocurre un error al ejecutar la consulta.
	 */
	public ResponseDTO findById(Long id) throws Exception {
		String sql = "SELECT " + selectColumns() + " FROM " + tableName() + " WHERE id = ?";

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next())
					return null;
				return mapper().toResponse(fromResultSet(rs));
			}
		}
	}

	/**
	 * Recupera todos los registros de la tabla.
	 *
	 * @return Lista de DTOs de respuesta.
	 * @throws Exception Si ocurre un error al ejecutar la consulta.
	 */
	public List<ResponseDTO> findAll() throws Exception {
		String sql = "SELECT " + selectColumns() + " FROM " + tableName();
		List<ResponseDTO> list = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				list.add(mapper().toResponse(fromResultSet(rs)));
			}
		}

		return list;
	}

	/**
	 * Inserta un nuevo registro en la tabla.
	 *
	 * @param req DTO con los datos para la inserción.
	 * @return ID generado del registro insertado o null si no se pudo obtener.
	 * @throws Exception Si ocurre un error al ejecutar la operación.
	 */
	public Long insert(RequestDTO req) throws Exception {
		Entity entity = mapper().fromRequest(req);
		String sql = "INSERT INTO " + tableName() +
				" (" + insertColumnsSql() + ") VALUES (" + generatePlaceholders(insertColumnsSql()) + ")";

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			fillInsert(stmt, entity);
			stmt.executeUpdate();

			try (ResultSet keys = stmt.getGeneratedKeys()) {
				return keys.next() ? keys.getLong(1) : null;
			}
		}
	}

	/**
	 * Inserta un registro y retorna el DTO correspondiente al registro creado.
	 *
	 * @param req DTO de inserción.
	 * @return DTO de respuesta con los datos insertados y su ID.
	 * @throws Exception Si ocurre un error al ejecutar la operación.
	 */
	public ResponseDTO insertAndReturn(RequestDTO req) throws Exception {
		Long id = insert(req);
		Entity entity = mapper().fromRequest(req);
		entity.setId(id);
		return mapper().toResponse(entity);
	}

	/**
	 * Actualiza completamente un registro existente por ID.
	 *
	 * @param id  Identificador del registro.
	 * @param req DTO con los nuevos datos.
	 * @return true si se actualizó correctamente, false si no existía.
	 * @throws Exception Si ocurre un error al ejecutar la operación.
	 */
	public boolean update(Long id, RequestDTO req) throws Exception {
		Entity entity = mapper().fromRequest(req);
		entity.setId(id);

		String sql = "UPDATE " + tableName() + " SET " + updateSetSql() + " WHERE id = ?";

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			fillUpdate(stmt, entity);
			return stmt.executeUpdate() > 0;
		}
	}

	/**
	 * Actualiza un registro por ID y retorna el DTO resultante.
	 *
	 * @param id  Identificador del registro.
	 * @param req DTO con los datos de actualización.
	 * @return DTO actualizado o null si no existe.
	 * @throws Exception Si ocurre un error al acceder a la base de datos.
	 */
	public ResponseDTO updateAndReturn(Long id, RequestDTO req) throws Exception {
		boolean updated = update(id, req);
		if (!updated)
			return null;
		return findById(id);
	}

	/**
	 * Aplica un parche parcial a un registro existente.
	 *
	 * @param id    Identificador del registro.
	 * @param patch DTO con los campos parcialmente modificados.
	 * @return true si se modificó al menos un campo, false si no hubo cambios.
	 * @throws Exception Si ocurre un error en la operación.
	 */
	public boolean patch(Long id, PatchDTO patch) throws Exception {
		StringBuilder sql = new StringBuilder("UPDATE " + tableName() + " SET ");
		List<Object> params = new ArrayList<>();

		fillPatch(sql, params, patch);
		if (params.isEmpty())
			return false;

		sql.append(" WHERE id = ?");
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

			int i = 1;
			for (Object param : params)
				stmt.setObject(i++, param);
			stmt.setLong(i, id);

			return stmt.executeUpdate() > 0;
		}
	}

	/**
	 * Aplica un PATCH parcial y retorna el DTO actualizado.
	 *
	 * @param id    Identificador del registro.
	 * @param patch DTO con los cambios parciales.
	 * @return DTO actualizado o null si no se realizó ninguna modificación.
	 * @throws Exception Si ocurre un error en la operación.
	 */
	public ResponseDTO patchAndReturn(Long id, PatchDTO patch) throws Exception {
		boolean updated = patch(id, patch);
		if (!updated)
			return null;
		return findById(id);
	}

	/**
	 * Elimina un registro de la tabla por su ID.
	 *
	 * @param id Identificador del registro.
	 * @return true si se eliminó correctamente, false si no existía.
	 * @throws Exception Si ocurre un error en la operación.
	 */
	public boolean delete(Long id) throws Exception {
		String sql = "DELETE FROM " + tableName() + " WHERE id = ?";
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			return stmt.executeUpdate() > 0;
		}
	}

	/* ----------------------- UTILIDADES ----------------------- */

	/**
	 * Genera placeholders para sentencias SQL basadas en el número de columnas.
	 *
	 * @param columns Cadena con nombres de columnas separados por coma.
	 * @return Cadena de placeholders en formato "?, ?, ...".
	 */
	private String generatePlaceholders(String columns) {
		int count = columns.split(",").length;
		return String.join(", ", java.util.Collections.nCopies(count, "?"));
	}
}
