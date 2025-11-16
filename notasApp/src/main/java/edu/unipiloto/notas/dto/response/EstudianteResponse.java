package edu.unipiloto.notas.dto.response;

import edu.unipiloto.notas.dto.response.template.BaseResponseDTO;

/**
 * DTO de respuesta para la entidad {@code Estudiante}.
 *
 * <p>
 * Extiende {@link BaseResponseDTO}, proporcionando atributos comunes como
 * identificador, fecha de creación y fecha de actualización. Representa la
 * estructura utilizada para retornar información del estudiante en operaciones
 * de consulta.
 * </p>
 */
public class EstudianteResponse extends BaseResponseDTO {

    /** Nombres del estudiante. */
    private String nombres;

    /** Apellidos del estudiante. */
    private String apellidos;

    /** Documento de identificación del estudiante. */
    private String documento;

    /** Correo electrónico registrado del estudiante. */
    private String correo;

    /** Ciclo académico al que pertenece el estudiante. */
    private String ciclo;

    /**
     * Obtiene los nombres del estudiante.
     *
     * @return nombres del estudiante
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del estudiante.
     *
     * @param nombres nombres del estudiante
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del estudiante.
     *
     * @return apellidos del estudiante
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del estudiante.
     *
     * @param apellidos apellidos del estudiante
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el documento de identificación del estudiante.
     *
     * @return documento del estudiante
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el documento de identificación del estudiante.
     *
     * @param documento documento del estudiante
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el correo del estudiante.
     *
     * @return correo del estudiante
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del estudiante.
     *
     * @param correo correo del estudiante
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el ciclo académico del estudiante.
     *
     * @return ciclo académico
     */
    public String getCiclo() {
        return ciclo;
    }

    /**
     * Establece el ciclo académico del estudiante.
     *
     * @param ciclo ciclo académico del estudiante
     */
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
}
