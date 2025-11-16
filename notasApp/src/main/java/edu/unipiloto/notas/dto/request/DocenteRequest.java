package edu.unipiloto.notas.dto.request;

/**
 * Representa la solicitud de creación de un docente.
 * Contiene los datos requeridos para registrar un nuevo docente
 * en el sistema académico.
 */
public class DocenteRequest {

    /** Nombres del docente. */
    private String nombres;

    /** Apellidos del docente. */
    private String apellidos;

    /** Correo institucional o personal del docente. */
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
     * Obtiene el correo del docente.
     *
     * @return correo del docente
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del docente.
     *
     * @param correo correo del docente
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
