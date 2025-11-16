package edu.unipiloto.notas.mapper.template;

import edu.unipiloto.notas.model.template.BaseModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Mapper abstracto unificado para entidades con BaseModel y DTOs.
 * Proporciona utilidades comunes y las operaciones contractuales
 * que deben implementar los mappers concretos.
 */
public abstract class BaseMapper<
        Entity extends BaseModel,
        RequestDTO,
        ResponseDTO,
        PatchDTO
        > {

    /* -------------------- Métodos comunes -------------------- */

    /** Mapea campos comunes del ResultSet a la entidad. */
    protected void fillCommonFromResultSet(ResultSet rs, Entity entity) throws SQLException {
        entity.setId(rs.getLong("id"));

        Timestamp created = rs.getTimestamp("created_at");
        Timestamp updated = rs.getTimestamp("updated_at");

        entity.setCreatedAt(created != null ? created.toLocalDateTime() : null);
        entity.setUpdatedAt(updated != null ? updated.toLocalDateTime() : null);
    }

    /* -------------------- Métodos que deben implementar los mappers concretos -------------------- */

    /** Convierte una entidad a ResponseDTO. */
    public abstract ResponseDTO toResponse(Entity entity);

    /** Construye una entidad desde RequestDTO. */
    public abstract Entity fromRequest(RequestDTO request);

    /** Aplica actualización completa (PUT). */
    public abstract void applyUpdate(Entity entity, RequestDTO request);

    /** Aplica actualización parcial (PATCH). */
    public abstract void applyPatch(Entity entity, PatchDTO patch);

    /* -------------------- Métodos utilitarios opcionales -------------------- */

    /** Permite validar datos previos a persistencia. */
    protected void validateForUpdate(Entity entity) { }

    /** Permite validaciones específicas en parches. */
    protected void validateForPatch(PatchDTO patch) { }
}
