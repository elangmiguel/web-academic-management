package edu.unipiloto.notas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import edu.unipiloto.notas.dto.request.NotaRequest;
import edu.unipiloto.notas.dto.patch.NotaPatch;
import edu.unipiloto.notas.dto.response.NotaResponse;
import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.Nota;

/**
 * Mapper para la entidad {@link Nota}.
 * Gestiona la conversión entre Entity, RequestDTO, ResponseDTO y PatchDTO,
 * además de utilidades JDBC para INSERT, UPDATE y construcción dinámica de PATCH.
 */
public class NotaMapper extends BaseMapper<
        Nota,
        NotaRequest,
        NotaResponse,
        NotaPatch
> {

    /**
     * Convierte una entidad {@link Nota} en un {@link NotaResponse}.
     *
     * @param entity entidad a convertir
     * @return DTO serializado o null si la entidad es null
     */
    @Override
    public NotaResponse toResponse(Nota entity) {
        if (entity == null) return null;

        NotaResponse dto = new NotaResponse();
        dto.setId(entity.getId());
        dto.setEstudianteId(entity.getEstudianteId());
        dto.setAsignaturaId(entity.getAsignaturaId());
        dto.setNota1(entity.getNota1());
        dto.setNota2(entity.getNota2());
        dto.setNotaFinal(entity.getNotaFinal());
        dto.setObservaciones(entity.getObservaciones());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    /**
     * Construye una entidad {@link Nota} desde un {@link NotaRequest}.
     *
     * @param req DTO con datos de entrada
     * @return nueva entidad o null si req es null
     */
    @Override
    public Nota fromRequest(NotaRequest req) {
        if (req == null) return null;

        Nota n = new Nota();
        n.setEstudianteId(req.getEstudianteId());
        n.setAsignaturaId(req.getAsignaturaId());
        n.setNota1(req.getNota1());
        n.setNota2(req.getNota2());
        n.setNotaFinal(req.getNotaFinal());
        n.setObservaciones(req.getObservaciones());

        return n;
    }

    /**
     * Aplica actualización completa (PUT) sobre una entidad existente.
     *
     * @param entity entidad objetivo
     * @param req DTO con nuevos valores
     */
    @Override
    public void applyUpdate(Nota entity, NotaRequest req) {
        if (entity == null || req == null) return;

        entity.setEstudianteId(req.getEstudianteId());
        entity.setAsignaturaId(req.getAsignaturaId());
        entity.setNota1(req.getNota1());
        entity.setNota2(req.getNota2());
        entity.setNotaFinal(req.getNotaFinal());
        entity.setObservaciones(req.getObservaciones());
    }

    /**
     * Aplica actualización parcial (PATCH) a una entidad.
     *
     * @param entity entidad a modificar
     * @param patch DTO con campos opcionales
     */
    @Override
    public void applyPatch(Nota entity, NotaPatch patch) {
        if (entity == null || patch == null) return;

        if (patch.getEstudianteId() != null) entity.setEstudianteId(patch.getEstudianteId());
        if (patch.getAsignaturaId() != null) entity.setAsignaturaId(patch.getAsignaturaId());
        if (patch.getNota1() != null) entity.setNota1(patch.getNota1());
        if (patch.getNota2() != null) entity.setNota2(patch.getNota2());
        if (patch.getNotaFinal() != null) entity.setNotaFinal(patch.getNotaFinal());
        if (patch.getObservaciones() != null) entity.setObservaciones(patch.getObservaciones());
    }

    /**
     * Construye una entidad {@link Nota} a partir de un {@link ResultSet}.
     *
     * @param rs ResultSet JDBC
     * @return entidad Nota construida
     * @throws SQLException si ocurre un error leyendo el ResultSet
     */
    public Nota fromResultSet(ResultSet rs) throws SQLException {
        Nota n = new Nota();

        fillCommonFromResultSet(rs, n);

        n.setEstudianteId(rs.getLong("estudiante_id"));
        n.setAsignaturaId(rs.getLong("asignatura_id"));
        n.setNota1(rs.getDouble("nota1"));
        n.setNota2(rs.getDouble("nota2"));
        n.setNotaFinal(rs.getDouble("nota_final"));
        n.setObservaciones(rs.getString("observaciones"));

        return n;
    }

    /**
     * Llena parámetros de INSERT para {@link Nota}.
     *
     * @param stmt PreparedStatement destino
     * @param n entidad con valores a insertar
     * @throws SQLException si ocurre un error al asignar valores
     */
    public void fillInsert(PreparedStatement stmt, Nota n) throws SQLException {
        stmt.setLong(1, n.getEstudianteId());
        stmt.setLong(2, n.getAsignaturaId());
        stmt.setDouble(3, n.getNota1());
        stmt.setDouble(4, n.getNota2());
        stmt.setDouble(5, n.getNotaFinal());
        stmt.setString(6, n.getObservaciones());
    }

    /**
     * Llena parámetros de UPDATE para {@link Nota}.
     *
     * @param stmt PreparedStatement destino
     * @param n entidad con valores actualizados
     * @throws SQLException si ocurre un error al asignar valores
     */
    public void fillUpdate(PreparedStatement stmt, Nota n) throws SQLException {
        stmt.setLong(1, n.getEstudianteId());
        stmt.setLong(2, n.getAsignaturaId());
        stmt.setDouble(3, n.getNota1());
        stmt.setDouble(4, n.getNota2());
        stmt.setDouble(5, n.getNotaFinal());
        stmt.setString(6, n.getObservaciones());
        stmt.setLong(7, n.getId());
    }

    /**
     * Construye dinámicamente el fragmento SQL de un PATCH para {@link Nota}.
     *
     * @param sql builder del fragmento SQL SET
     * @param params parámetros para PreparedStatement
     * @param patch DTO con campos opcionales
     */
    public void fillPatch(StringBuilder sql, List<Object> params, NotaPatch patch) {

        if (patch.getEstudianteId() != null) {
            sql.append("estudiante_id = ?, ");
            params.add(patch.getEstudianteId());
        }
        if (patch.getAsignaturaId() != null) {
            sql.append("asignatura_id = ?, ");
            params.add(patch.getAsignaturaId());
        }
        if (patch.getNota1() != null) {
            sql.append("nota1 = ?, ");
            params.add(patch.getNota1());
        }
        if (patch.getNota2() != null) {
            sql.append("nota2 = ?, ");
            params.add(patch.getNota2());
        }
        if (patch.getNotaFinal() != null) {
            sql.append("nota_final = ?, ");
            params.add(patch.getNotaFinal());
        }
        if (patch.getObservaciones() != null) {
            sql.append("observaciones = ?, ");
            params.add(patch.getObservaciones());
        }
    }
}
