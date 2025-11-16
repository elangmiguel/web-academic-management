package edu.unipiloto.notas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import edu.unipiloto.notas.dto.request.EstudianteRequest;
import edu.unipiloto.notas.dto.patch.EstudiantePatch;
import edu.unipiloto.notas.dto.response.EstudianteResponse;
import edu.unipiloto.notas.mapper.template.BaseMapper;
import edu.unipiloto.notas.model.Estudiante;

/**
 * Mapper para la entidad {@link Estudiante}.
 * Implementa conversión entre Entity, RequestDTO, ResponseDTO y PatchDTO,
 * además de utilidades JDBC para operaciones SQL comunes.
 */
public class EstudianteMapper extends BaseMapper<
        Estudiante,
        EstudianteRequest,
        EstudianteResponse,
        EstudiantePatch
> {

    /**
     * Convierte una entidad {@link Estudiante} en un DTO {@link EstudianteResponse}.
     *
     * @param entity instancia de la entidad a convertir
     * @return DTO con los datos serializados, o null si la entidad es null
     */
    @Override
    public EstudianteResponse toResponse(Estudiante entity) {
        if (entity == null) return null;

        EstudianteResponse dto = new EstudianteResponse();
        dto.setId(entity.getId());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setDocumento(entity.getDocumento());
        dto.setCorreo(entity.getCorreo());
        dto.setCiclo(entity.getCiclo());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    /**
     * Crea una entidad {@link Estudiante} a partir de un {@link EstudianteRequest}.
     *
     * @param req DTO con los datos de entrada
     * @return nueva entidad Estudiante, o null si req es null
     */
    @Override
    public Estudiante fromRequest(EstudianteRequest req) {
        if (req == null) return null;

        Estudiante e = new Estudiante();
        e.setNombres(req.getNombres());
        e.setApellidos(req.getApellidos());
        e.setDocumento(req.getDocumento());
        e.setCorreo(req.getCorreo());
        e.setCiclo(req.getCiclo());
        return e;
    }

    /**
     * Aplica una actualización completa (PUT) sobre una entidad existente.
     *
     * @param entity entidad a modificar
     * @param req DTO con los nuevos valores
     */
    @Override
    public void applyUpdate(Estudiante entity, EstudianteRequest req) {
        if (entity == null || req == null) return;

        entity.setNombres(req.getNombres());
        entity.setApellidos(req.getApellidos());
        entity.setDocumento(req.getDocumento());
        entity.setCorreo(req.getCorreo());
        entity.setCiclo(req.getCiclo());
    }

    /**
     * Aplica una actualización parcial (PATCH) según los valores presentes
     * en el DTO {@link EstudiantePatch}.
     *
     * @param entity entidad a modificar
     * @param patch DTO con campos opcionales
     */
    @Override
    public void applyPatch(Estudiante entity, EstudiantePatch patch) {
        if (entity == null || patch == null) return;

        if (patch.getNombres() != null) entity.setNombres(patch.getNombres());
        if (patch.getApellidos() != null) entity.setApellidos(patch.getApellidos());
        if (patch.getDocumento() != null) entity.setDocumento(patch.getDocumento());
        if (patch.getCorreo() != null) entity.setCorreo(patch.getCorreo());
        if (patch.getCiclo() != null) entity.setCiclo(patch.getCiclo());
    }

    /**
     * Construye una entidad {@link Estudiante} desde un {@link ResultSet}.
     * Utiliza {@link BaseMapper#fillCommonFromResultSet} para campos comunes.
     *
     * @param rs conjunto de resultados JDBC
     * @return entidad Estudiante construida
     * @throws SQLException si ocurre un error al leer el ResultSet
     */
    public Estudiante fromResultSet(ResultSet rs) throws SQLException {
        Estudiante e = new Estudiante();

        fillCommonFromResultSet(rs, e);

        e.setNombres(rs.getString("nombres"));
        e.setApellidos(rs.getString("apellidos"));
        e.setDocumento(rs.getString("documento"));
        e.setCorreo(rs.getString("correo"));
        e.setCiclo(rs.getString("ciclo"));
        return e;
    }

    /**
     * Llena los parámetros del {@link PreparedStatement} para una operación INSERT.
     *
     * @param stmt PreparedStatement con placeholders
     * @param e entidad con los valores a insertar
     * @throws SQLException si ocurre un error al asignar los valores
     */
    public void fillInsert(PreparedStatement stmt, Estudiante e) throws SQLException {
        stmt.setString(1, e.getNombres());
        stmt.setString(2, e.getApellidos());
        stmt.setString(3, e.getDocumento());
        stmt.setString(4, e.getCorreo());
        stmt.setString(5, e.getCiclo());
    }

    /**
     * Llena los parámetros del {@link PreparedStatement} para una operación UPDATE.
     *
     * @param stmt PreparedStatement con placeholders
     * @param e entidad con los valores actualizados
     * @throws SQLException si ocurre un error al asignar los valores
     */
    public void fillUpdate(PreparedStatement stmt, Estudiante e) throws SQLException {
        stmt.setString(1, e.getNombres());
        stmt.setString(2, e.getApellidos());
        stmt.setString(3, e.getDocumento());
        stmt.setString(4, e.getCorreo());
        stmt.setString(5, e.getCiclo());
        stmt.setLong(6, e.getId());
    }

    /**
     * Construye dinámicamente el fragmento SQL para una operación PATCH,
     * agregando solo los campos presentes en {@link EstudiantePatch}.
     *
     * @param sql builder del fragmento SQL
     * @param params lista de parámetros para el PreparedStatement
     * @param patch DTO con campos opcionales
     */
    public void fillPatch(StringBuilder sql, List<Object> params, EstudiantePatch patch) {

        if (patch.getNombres() != null) {
            sql.append("nombres = ?, ");
            params.add(patch.getNombres());
        }
        if (patch.getApellidos() != null) {
            sql.append("apellidos = ?, ");
            params.add(patch.getApellidos());
        }
        if (patch.getDocumento() != null) {
            sql.append("documento = ?, ");
            params.add(patch.getDocumento());
        }
        if (patch.getCorreo() != null) {
            sql.append("correo = ?, ");
            params.add(patch.getCorreo());
        }
        if (patch.getCiclo() != null) {
            sql.append("ciclo = ?, ");
            params.add(patch.getCiclo());
        }
    }
}
