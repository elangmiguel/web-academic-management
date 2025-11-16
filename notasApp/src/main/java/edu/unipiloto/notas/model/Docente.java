package edu.unipiloto.notas.model;

import edu.unipiloto.notas.model.template.BaseModel;

/**
 * Modelo que representa a un docente dentro del sistema académico.
 *
 * <p>Incluye información básica de identificación como nombres,
 * apellidos y correo electrónico. Hereda los campos de auditoría
 * definidos en {@link BaseModel}.</p>
 */
public class Docente extends BaseModel {

    /**
     * Nombres del docente.
     */
    private String nombres;

    /**
     * Apellidos del docente.
     */
    private String apellidos;

    /**
     * Correo electrónico institucional o personal del docente.
     */
    private String correo;

    /**
     * Constructor por defecto.
     * Permite la creación de instancias vacías para inicialización posterior.
     */
    public Docente() {
    }

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
     * @param nombres nombres completos
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
     * @param apellidos apellidos completos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo electrónico del docente.
     *
     * @return dirección de correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del docente.
     *
     * @param correo dirección de correo válida
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
