package edu.unipiloto.notas.dto.patch;

/**
 * DTO para operaciones de actualización parcial (PATCH) sobre la entidad {@code Estudiante}.
 *
 * <p>
 * Este objeto encapsula los campos opcionales que pueden ser modificados durante
 * una actualización parcial. Solo los atributos no nulos serán aplicados en la sentencia
 * SQL generada por el DAO correspondiente.
 * </p>
 *
 * <p>
 * Campos disponibles para actualización parcial:
 * <ul>
 *   <li>{@code nombres}: Nombres del estudiante.</li>
 *   <li>{@code apellidos}: Apellidos del estudiante.</li>
 *   <li>{@code documento}: Documento de identidad.</li>
 *   <li>{@code correo}: Correo electrónico institucional o personal.</li>
 *   <li>{@code ciclo}: Ciclo académico actual.</li>
 * </ul>
 * </p>
 */
public class EstudiantePatch {

    /** Nombres del estudiante (opcional). */
    private String nombres;

    /** Apellidos del estudiante (opcional). */
    private String apellidos;

    /** Número de documento del estudiante (opcional). */
    private String documento;

    /** Correo del estudiante (opcional). */
    private String correo;

    /** Ciclo académico del estudiante (opcional). */
    private String ciclo;

    /**
     * Retorna los nombres del estudiante.
     *
     * @return nombres o {@code null} si no serán modificados
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del estudiante para la actualización parcial.
     *
     * @param nombres nuevos nombres o {@code null} para omitir actualización
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Retorna los apellidos del estudiante.
     *
     * @return apellidos o {@code null} si no serán modificados
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del estudiante para la actualización parcial.
     *
     * @param apellidos nuevos apellidos o {@code null} para omitir actualización
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Retorna el documento del estudiante.
     *
     * @return documento o {@code null} si no será modificado
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el documento del estudiante para la actualización parcial.
     *
     * @param documento nuevo documento o {@code null} para omitir actualización
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Retorna el correo del estudiante.
     *
     * @return correo o {@code null} si no será modificado
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del estudiante para la actualización parcial.
     *
     * @param correo nuevo correo o {@code null} para omitir actualización
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna el ciclo académico del estudiante.
     *
     * @return ciclo o {@code null} si no será modificado
     */
    public String getCiclo() {
        return ciclo;
    }

    /**
     * Establece el ciclo académico del estudiante para la actualización parcial.
     *
     * @param ciclo nuevo ciclo o {@code null} para omitir actualización
     */
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
}
