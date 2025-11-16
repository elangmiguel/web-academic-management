package edu.unipiloto.notas.mapper.template;

import edu.unipiloto.notas.model.template.BaseModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Clase base abstracta para la definición de mappers encargados de transformar
 * entidades del dominio que extienden {@link BaseModel} hacia sus distintos
 * DTOs (Request, Response y Patch), así como para construir entidades a partir
 * de dichos DTOs.
 *
 * <p>
 * Centraliza la lógica común requerida por los mappers concretos, tales como
 * el mapeo de campos estándar presentes en todas las entidades y la definición
 * de los métodos contractuales que cada mapper debe implementar.
 * </p>
 *
 * @param <Entity>      tipo de la entidad que extiende {@link BaseModel}
 * @param <RequestDTO>  DTO utilizado para operaciones de creación y
 *                      actualización completa
 * @param <ResponseDTO> DTO utilizado para representar datos enviados al cliente
 * @param <PatchDTO>    DTO utilizado para actualizaciones parciales
 */
public abstract class BaseMapper<Entity extends BaseModel, RequestDTO, ResponseDTO, PatchDTO> {

	/*
	 * ----------------------------------------------------------
	 * Métodos comunes
	 * ----------------------------------------------------------
	 */

	/**
	 * Transfiere los campos comunes del {@link ResultSet} hacia una entidad.
	 *
	 * <p>
	 * Realiza el mapeo del identificador primario, la fecha de creación
	 * ({@code created_at}) y la fecha de actualización ({@code updated_at}).
	 * </p>
	 *
	 * @param rs     ResultSet obtenido de la consulta
	 * @param entity instancia de la entidad a poblar
	 * @throws SQLException si ocurre un error al leer los datos del ResultSet
	 */
	protected void fillCommonFromResultSet(ResultSet rs, Entity entity) throws SQLException {
		entity.setId(rs.getLong("id"));

		Timestamp created = rs.getTimestamp("created_at");
		Timestamp updated = rs.getTimestamp("updated_at");

		entity.setCreatedAt(created != null ? created.toLocalDateTime() : null);
		entity.setUpdatedAt(updated != null ? updated.toLocalDateTime() : null);
	}

	/*
	 * ----------------------------------------------------------
	 * Métodos que deben implementar los mappers concretos
	 * ----------------------------------------------------------
	 */

	/**
	 * Convierte una entidad a su correspondiente ResponseDTO.
	 *
	 * @param entity entidad a transformar
	 * @return un DTO de respuesta con los datos listos para envío al cliente
	 */
	public abstract ResponseDTO toResponse(Entity entity);

	/**
	 * Construye una entidad nueva a partir de un RequestDTO.
	 *
	 * @param request DTO que contiene los datos enviados por el cliente
	 * @return una nueva instancia de la entidad con los datos del request
	 */
	public abstract Entity fromRequest(RequestDTO request);

	/**
	 * Aplica una actualización completa (operación PUT) a la entidad,
	 * sobrescribiendo todos sus campos modificables.
	 *
	 * @param entity  entidad existente a actualizar
	 * @param request DTO con los nuevos valores
	 */
	public abstract void applyUpdate(Entity entity, RequestDTO request);

	/**
	 * Aplica una actualización parcial (operación PATCH) sobre la entidad,
	 * modificando únicamente los campos presentes en el PatchDTO.
	 *
	 * @param entity entidad existente a modificar
	 * @param patch  DTO con los campos a actualizar parcialmente
	 */
	public abstract void applyPatch(Entity entity, PatchDTO patch);

	/*
	 * ----------------------------------------------------------
	 * Métodos utilitarios opcionales
	 * ----------------------------------------------------------
	 */

	/**
	 * Hook opcional para ejecutar validaciones antes de persistir
	 * durante una actualización completa.
	 *
	 * @param entity entidad modificada
	 */
	protected void validateForUpdate(Entity entity) {
	}

	/**
	 * Hook opcional para validar información proporcionada en un PatchDTO.
	 *
	 * @param patch DTO de actualización parcial
	 */
	protected void validateForPatch(PatchDTO patch) {
	}
}
