package edu.unipiloto.notas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import edu.unipiloto.notas.dto.request.DocenteRequest;
import edu.unipiloto.notas.dto.patch.DocentePatch;
import edu.unipiloto.notas.dto.response.DocenteResponse;
import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.Docente;

/**
 * Mapper para la entidad {@link Docente}.
 * Gestiona la conversión entre Entity, RequestDTO, ResponseDTO y PatchDTO,
 * además de utilidades JDBC para operaciones INSERT, UPDATE y PATCH dinámico.
 */
public class DocenteMapper extends BaseMapper<
        Docente,
        DocenteRequest,
        DocenteResponse,
        DocentePatch
> {

    /**
     * Convierte una entidad {@link Docente} en un {@link DocenteResponse}.
     *
     * @param entity entidad a convertir
     * @return DTO serializado o null si la entidad es null
     */
    @Override
    public DocenteResponse toResponse(Docente entity) {
        if (entity == null) return null;

        DocenteResponse dto = new DocenteResponse();
        dto.setId(entity.getId());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setCorreo(entity.getCorreo());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    /**
     * Construye una entidad {@link Docente} desde un {@link DocenteRequest}.
     *
     * @param req DTO con datos de entrada
     * @return nueva entidad o null si req es null
     */
    @Override
    public Docente fromRequest(DocenteRequest req) {
        if (req == null) return null;

        Docente d = new Docente();
        d.setNombres(req.getNombres());
        d.setApellidos(req.getApellidos());
        d.setCorreo(req.getCorreo());

        return d;
    }

    /**
     * Aplica actualización completa (PUT) sobre una entidad existente.
     *
     * @param entity entidad destino
     * @param req DTO con nuevos valores
     */
    @Override
    public void applyUpdate(Docente entity, DocenteRequest req) {
        if (entity == null || req == null) return;

        entity.setNombres(req.getNombres());
        entity.setApellidos(req.getApellidos());
        entity.setCorreo(req.getCorreo());
    }

    /**
     * Aplica actualización parcial (PATCH) a la entidad.
     *
     * @param entity entidad objetivo
     * @param patch DTO con campos opcionales
     */
    @Override
    public void applyPatch(Docente entity, DocentePatch patch) {
        if (entity == null || patch == null) return;

        if (patch.getNombres() != null) entity.setNombres(patch.getNombres());
        if (patch.getApellidos() != null) entity.setApellidos(patch.getApellidos());
        if (patch.getCorreo() != null) entity.setCorreo(patch.getCorreo());
    }

    /**
     * Construye una entidad {@link Docente} a partir de un {@link ResultSet}.
     *
     * @param rs ResultSet JDBC
     * @return entidad mapeada
     * @throws SQLException si ocurre un error al leer el ResultSet
     */
    public Docente fromResultSet(ResultSet rs) throws SQLException {
        Docente d = new Docente();

        fillCommonFromResultSet(rs, d);

        d.setNombres(rs.getString("nombres"));
        d.setApellidos(rs.getString("apellidos"));
        d.setCorreo(rs.getString("correo"));

        return d;
    }

    /**
     * Llena los parámetros de un INSERT para {@link Docente}.
     *
     * @param stmt PreparedStatement a completar
     * @param d entidad con valores a insertar
     * @throws SQLException si ocurre un error asignando valores
     */
    public void fillInsert(PreparedStatement stmt, Docente d) throws SQLException {
        stmt.setString(1, d.getNombres());
        stmt.setString(2, d.getApellidos());
        stmt.setString(3, d.getCorreo());
    }

    /**
     * Llena los parámetros de un UPDATE para {@link Docente}.
     *
     * @param stmt PreparedStatement a completar
     * @param d entidad con nuevos valores
     * @throws SQLException si ocurre un error asignando valores
     */
    public void fillUpdate(PreparedStatement stmt, Docente d) throws SQLException {
        stmt.setString(1, d.getNombres());
        stmt.setString(2, d.getApellidos());
        stmt.setString(3, d.getCorreo());
        stmt.setLong(4, d.getId());
    }

    /**
     * Construye dinámicamente el fragmento SQL para un PATCH de {@link Docente}.
     *
     * @param sql builder del fragmento SQL SET
     * @param params parámetros para PreparedStatement
     * @param patch DTO con valores opcionales
     */
    public void fillPatch(StringBuilder sql, List<Object> params, DocentePatch patch) {

        if (patch.getNombres() != null) {
            sql.append("nombres = ?, ");
            params.add(patch.getNombres());
        }
        if (patch.getApellidos() != null) {
            sql.append("apellidos = ?, ");
            params.add(patch.getApellidos());
        }
        if (patch.getCorreo() != null) {
            sql.append("correo = ?, ");
            params.add(patch.getCorreo());
        }
    }
}
