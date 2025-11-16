package edu.unipiloto.notas.dto.patch;

/**
 * DTO de actualización parcial (PATCH) para la entidad {@code Docente}.
 *
 * <p>
 * Esta clase encapsula los campos opcionales que pueden ser modificados durante
 * una operación PATCH. Solo los valores no nulos enviados por el cliente serán
 * aplicados por el DAO correspondiente.
 * </p>
 *
 * <p>
 * Campos soportados para modificación parcial:
 * <ul>
 *   <li>{@code nombres}: Nombres del docente.</li>
 *   <li>{@code apellidos}: Apellidos del docente.</li>
 *   <li>{@code correo}: Correo electrónico institucional o personal.</li>
 * </ul>
 * </p>
 */
public class DocentePatch {

    /** Nombres del docente (opcional). */
    private String nombres;

    /** Apellidos del docente (opcional). */
    private String apellidos;

    /** Correo electrónico del docente (opcional). */
    private String correo;

    /**
     * Retorna los nombres a actualizar.
     *
     * @return nombres o {@code null} si no cambian
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres para esta actualización parcial.
     *
     * @param nombres nuevos nombres o {@code null} para omitir cambio
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Retorna los apellidos a actualizar.
     *
     * @return apellidos o {@code null} si no cambian
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos para esta actualización parcial.
     *
     * @param apellidos nuevos apellidos o {@code null} para omitir cambio
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Retorna el correo electrónico a actualizar.
     *
     * @return correo o {@code null} si no cambia
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico para esta actualización parcial.
     *
     * @param correo nuevo correo o {@code null} para omitir cambio
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
