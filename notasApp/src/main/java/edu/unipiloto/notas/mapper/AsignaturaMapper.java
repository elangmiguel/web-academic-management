package edu.unipiloto.notas.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.unipiloto.notas.dto.request.AsignaturaRequest;
import edu.unipiloto.notas.dto.patch.AsignaturaPatch;
import edu.unipiloto.notas.dto.response.AsignaturaResponse;
import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.Asignatura;

/**
 * Mapper para la entidad {@link Asignatura}.
 * Proporciona conversión entre Entity, RequestDTO, ResponseDTO y PatchDTO,
 * así como métodos auxiliares para operaciones JDBC comunes.
 */
public class AsignaturaMapper extends BaseMapper<
        Asignatura,
        AsignaturaRequest,
        AsignaturaResponse,
        AsignaturaPatch
> {

    /**
     * Convierte una entidad {@link Asignatura} en un DTO {@link AsignaturaResponse}.
     *
     * @param entity instancia de Asignatura
     * @return DTO serializado o null si la entidad es null
     */
    @Override
    public AsignaturaResponse toResponse(Asignatura entity) {
        if (entity == null) return null;

        AsignaturaResponse dto = new AsignaturaResponse();
        dto.setId(entity.getId());
        dto.setDocenteId(entity.getDocenteId());
        dto.setNombre(entity.getNombre());
        dto.setIntensidadHoraria(entity.getIntensidadHoraria());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    /**
     * Crea una entidad {@link Asignatura} a partir de un {@link AsignaturaRequest}.
     *
     * @param req DTO de entrada
     * @return nueva entidad o null si req es null
     */
    @Override
    public Asignatura fromRequest(AsignaturaRequest req) {
        if (req == null) return null;

        Asignatura a = new Asignatura();
        a.setDocenteId(req.getDocenteId());
        a.setNombre(req.getNombre());
        a.setIntensidadHoraria(req.getIntensidadHoraria());
        return a;
    }

    /**
     * Aplica una actualización completa (PUT) sobre la entidad.
     *
     * @param entity entidad persistida
     * @param req DTO con nuevos valores
     */
    @Override
    public void applyUpdate(Asignatura entity, AsignaturaRequest req) {
        if (entity == null || req == null) return;

        entity.setDocenteId(req.getDocenteId());
        entity.setNombre(req.getNombre());
        entity.setIntensidadHoraria(req.getIntensidadHoraria());
    }

    /**
     * Aplica una actualización parcial (PATCH) sobre la entidad.
     *
     * @param entity entidad persistida
     * @param patch DTO con campos opcionales
     */
    @Override
    public void applyPatch(Asignatura entity, AsignaturaPatch patch) {
        if (entity == null || patch == null) return;

        if (patch.getDocenteId() != null) entity.setDocenteId(patch.getDocenteId());
        if (patch.getNombre() != null) entity.setNombre(patch.getNombre());
        if (patch.getIntensidadHoraria() != null)
            entity.setIntensidadHoraria(patch.getIntensidadHoraria());
    }

    /**
     * Construye una entidad {@link Asignatura} a partir de un {@link ResultSet}.
     *
     * @param rs conjunto JDBC
     * @return instancia de Asignatura
     * @throws SQLException si ocurre un error de lectura
     */
    public Asignatura fromResultSet(ResultSet rs) throws SQLException {
        Asignatura a = new Asignatura();

        fillCommonFromResultSet(rs, a);

        a.setDocenteId(rs.getLong("docente_id"));
        a.setNombre(rs.getString("nombre"));
        a.setIntensidadHoraria(rs.getInt("intensidad_horaria"));
        return a;
    }

    /**
     * Llena el {@link PreparedStatement} para una operación INSERT.
     *
     * @param stmt sentencia preparada
     * @param a entidad con datos a insertar
     * @throws SQLException si ocurre un error al asignar valores
     */
    public void fillInsert(PreparedStatement stmt, Asignatura a) throws SQLException {
        stmt.setLong(1, a.getDocenteId());
        stmt.setString(2, a.getNombre());
        stmt.setInt(3, a.getIntensidadHoraria());
    }

    /**
     * Llena el {@link PreparedStatement} para una operación UPDATE.
     *
     * @param stmt sentencia preparada
     * @param a entidad con datos a actualizar
     * @throws SQLException si ocurre un error al asignar valores
     */
    public void fillUpdate(PreparedStatement stmt, Asignatura a) throws SQLException {
        stmt.setLong(1, a.getDocenteId());
        stmt.setString(2, a.getNombre());
        stmt.setInt(3, a.getIntensidadHoraria());
        stmt.setLong(4, a.getId());
    }

    /**
     * Construye dinámicamente el fragmento SQL para un PATCH,
     * agregando solo los campos presentes en {@link AsignaturaPatch}.
     *
     * @param sql builder del fragmento SQL
     * @param params parámetros acumulados
     * @param patch DTO con campos opcionales
     */
    public void fillPatch(StringBuilder sql, List<Object> params, AsignaturaPatch patch) {

        if (patch.getDocenteId() != null) {
            sql.append("docente_id = ?, ");
            params.add(patch.getDocenteId());
        }

        if (patch.getNombre() != null) {
            sql.append("nombre = ?, ");
            params.add(patch.getNombre());
        }

        if (patch.getIntensidadHoraria() != null) {
            sql.append("intensidad_horaria = ?, ");
            params.add(patch.getIntensidadHoraria());
        }
    }
}
