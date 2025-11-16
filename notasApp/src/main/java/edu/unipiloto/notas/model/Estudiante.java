package edu.unipiloto.notas.model;

import edu.unipiloto.notas.model.template.BaseModel;

/**
 * Modelo que representa a un estudiante dentro del sistema académico.
 *
 * <p>Incluye información básica de identificación como nombres,
 * apellidos, documento, correo y ciclo académico. Hereda los campos
 * de auditoría definidos en {@link BaseModel}.</p>
 */
public class Estudiante extends BaseModel {

    /**
     * Nombres del estudiante.
     */
    private String nombres;

    /**
     * Apellidos del estudiante.
     */
    private String apellidos;

    /**
     * Documento de identificación del estudiante.
     */
    private String documento;

    /**
     * Correo electrónico institucional o personal del estudiante.
     */
    private String correo;

    /**
     * Ciclo académico en el que se encuentra el estudiante.
     */
    private String ciclo;

    /**
     * Constructor por defecto.
     * Permite crear instancias vacías para inicialización posterior.
     */
    public Estudiante() {
    }

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
     * @param nombres nombres completos
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
     * @param apellidos apellidos completos
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
     * @param documento número de identificación
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el correo electrónico del estudiante.
     *
     * @return dirección de correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del estudiante.
     *
     * @param correo dirección de correo válida
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
     * @param ciclo identificación del ciclo
     */
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
}
