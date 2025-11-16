package edu.unipiloto.notas.dao.template;

import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.template.BaseModel;
import edu.unipiloto.notas.config.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación genérica del patrón DAO para entidades basadas en
 * {@link BaseModel}.
 * Proporciona operaciones CRUD estándar y soporte para actualizaciones
 * parciales (PATCH).
 *
 * <p>
 * Las subclases deben definir los aspectos específicos de cada tabla,
 * incluyendo el mapeo entre ResultSet y la entidad, construcción de sentencias
 * SQL,
 * y conversión entre Entity y sus DTO correspondientes.
 * </p>
 *
 * @param <Entity>      Tipo de la entidad persistida
 * @param <RequestDTO>  DTO utilizado para inserción y actualización completa
 * @param <ResponseDTO> DTO devuelto en las consultas
 * @param <PatchDTO>    DTO utilizado para actualización parcial
 */
public abstract class CrudDAO<Entity extends BaseModel, RequestDTO, ResponseDTO, PatchDTO> {

	/**
	 * @return Nombre de la tabla asociada a la entidad.
	 */
	protected abstract String tableName();

	/**
	 * @return Mapper responsable de convertir entre Entity y DTOs.
	 */
	protected abstract BaseMapper<Entity, RequestDTO, ResponseDTO, PatchDTO> mapper();

	/**
	 * @return Lista de columnas utilizadas en operaciones SELECT.
	 *         Por defecto retorna "*".
	 */
	protected String selectColumns() {
		return "*";
	}

	/**
	 * Construye una instancia de la entidad a partir de un ResultSet.
	 *
	 * @param rs ResultSet posicionado en la fila a mapear
	 * @return Instancia de la entidad
	 * @throws SQLException si ocurre un error de acceso a datos
	 */
	protected abstract Entity fromResultSet(ResultSet rs) throws SQLException;

	/**
	 * @return Lista de columnas utilizadas en la sentencia INSERT.
	 *         No debe incluir VALUES.
	 */
	protected abstract String insertColumnsSql();

	/**
	 * Establece los parámetros del PreparedStatement para una inserción.
	 *
	 * @param stmt   PreparedStatement asociado al INSERT
	 * @param entity Entidad creada a partir del RequestDTO
	 * @throws SQLException si ocurre un error al asignar parámetros
	 */
	protected abstract void fillInsert(PreparedStatement stmt, Entity entity) throws SQLException;

	/**
	 * @return Sentencia SET para UPDATE, sin incluir WHERE.
	 */
	protected abstract String updateSetSql();

	/**
	 * Establece los parámetros del PreparedStatement para una actualización
	 * completa.
	 *
	 * @param stmt   PreparedStatement asociado al UPDATE
	 * @param entity Entidad actualizada
	 * @throws SQLException si ocurre un error al asignar parámetros
	 */
	protected abstract void fillUpdate(PreparedStatement stmt, Entity entity) throws SQLException;

	/**
	 * Construye dinámicamente la sentencia SQL y los parámetros necesarios
	 * para realizar un PATCH parcial.
	 *
	 * @param sql    Acumulador de la sentencia SQL
	 * @param params Lista de parámetros correspondientes a las columnas a
	 *               actualizar
	 * @param patch  DTO con los campos parcialmente actualizados
	 */
	protected abstract void fillPatch(StringBuilder sql, List<Object> params, PatchDTO patch);

	/* ----------------------- LÓGICA CRUD GENÉRICA ----------------------- */

	/**
	 * Obtiene una conexión desde el datasource configurado.
	 *
	 * @return Conexión activa
	 * @throws Exception si no es posible obtener la conexión
	 */
	protected Connection getConnection() throws Exception {
		return ConnectionManager.getDataSource().getConnection();
	}

	/**
	 * Busca un registro por ID.
	 *
	 * @param id Identificador del registro
	 * @return DTO de respuesta o null si no existe
	 * @throws Exception si ocurre un error al consultar
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
	 * @return Lista de DTOs de respuesta
	 * @throws Exception si ocurre un error al consultar
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
	 * @param req DTO con los datos de inserción
	 * @return ID generado o null si no se pudo obtener
	 * @throws Exception si ocurre un error en la operación
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
	 * Inserta un registro y construye el DTO de respuesta resultante.
	 *
	 * @param req DTO de inserción
	 * @return ResponseDTO correspondiente al registro creado
	 * @throws Exception si ocurre un error en la operación
	 */
	public ResponseDTO insertAndReturn(RequestDTO req) throws Exception {
		Long id = insert(req);
		Entity entity = mapper().fromRequest(req);
		entity.setId(id);
		return mapper().toResponse(entity);
	}

	/**
	 * Actualiza completamente un registro existente.
	 *
	 * @param id  Identificador del registro
	 * @param req DTO con los datos actualizados
	 * @return true si se actualizó, false en caso contrario
	 * @throws Exception si ocurre un error en la operación
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
	 * Actualiza completamente un registro y retorna el DTO resultante.
	 *
	 * @param id  Identificador del registro
	 * @param req DTO de actualización
	 * @return DTO actualizado o null si no existe
	 * @throws Exception si ocurre un error de acceso a datos
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
	 * @param id    Identificador del registro
	 * @param patch DTO con los cambios parciales
	 * @return true si se actualizó al menos un campo, false si no hubo
	 *         modificaciones
	 * @throws Exception si ocurre un error en la operación
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
	 * Aplica un parche parcial y retorna el DTO resultante.
	 *
	 * @param id    Identificador del registro
	 * @param patch DTO con campos parcialmente modificados
	 * @return DTO actualizado o null si no existe o no hubo cambios
	 * @throws Exception si ocurre un error de acceso a datos
	 */
	public ResponseDTO patchAndReturn(Long id, PatchDTO patch) throws Exception {
		boolean updated = patch(id, patch);
		if (!updated)
			return null;
		return findById(id);
	}

	/**
	 * Elimina un registro por ID.
	 *
	 * @param id Identificador del registro
	 * @return true si se eliminó, false en caso contrario
	 * @throws Exception si ocurre un error en la operación
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
	 * Genera placeholders para una sentencia SQL según la cantidad de columnas
	 * especificadas.
	 *
	 * @param columns Cadena con columnas separadas por coma
	 * @return Cadena con placeholders en formato "?, ?, ...".
	 */
	private String generatePlaceholders(String columns) {
		int count = columns.split(",").length;
		return String.join(", ", java.util.Collections.nCopies(count, "?"));
	}
}
