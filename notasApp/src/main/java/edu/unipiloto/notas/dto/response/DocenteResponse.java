package edu.unipiloto.notas.dto.response;

import edu.unipiloto.notas.dto.response.template.BaseResponseDTO;

/**
 * DTO de respuesta para la entidad {@code Docente}.
 *
 * <p>
 * Extiende {@link BaseResponseDTO} para incluir datos comunes como el
 * identificador, la fecha de creación y la fecha de actualización.
 * Este DTO representa la estructura utilizada para devolver información
 * de docentes en operaciones de consulta.
 * </p>
 */
public class DocenteResponse extends BaseResponseDTO {

    /** Nombres del docente. */
    private String nombres;

    /** Apellidos del docente. */
    private String apellidos;

    /** Correo institucional o de contacto del docente. */
    private String correo;

    /**
     * Obtiene los nombres del docente.
     *
     * @return nombres del docente
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del docente.
     *
     * @param nombres nombres del docente
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del docente.
     *
     * @return apellidos del docente
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del docente.
     *
     * @param apellidos apellidos del docente
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo electrónico del docente.
     *
     * @return correo del docente
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del docente.
     *
     * @param correo correo del docente
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
