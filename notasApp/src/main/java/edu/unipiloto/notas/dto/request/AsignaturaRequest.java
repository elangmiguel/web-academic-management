package edu.unipiloto.notas.dto.request;

/**
 * Representa la estructura de datos necesaria para registrar una nueva asignatura.
 * Contiene las propiedades mínimas requeridas para una operación de creación (POST).
 */
public class AsignaturaRequest {

    /**
     * Identificador del docente responsable de la asignatura.
     */
    private Long docenteId;

    /**
     * Nombre oficial de la asignatura.
     */
    private String nombre;

    /**
     * Intensidad horaria semanal expresada en número de horas.
     */
    private int intensidadHoraria;

    /**
     * Obtiene el identificador del docente asignado.
     *
     * @return valor Long correspondiente al docente asociado.
     */
    public Long getDocenteId() {
        return docenteId;
    }

    /**
     * Define el identificador del docente responsable.
     *
     * @param docenteId identificador del docente.
     */
    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    /**
     * Obtiene el nombre de la asignatura.
     *
     * @return cadena con el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre de la asignatura.
     *
     * @param nombre cadena con el nombre de la asignatura.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la intensidad horaria semanal.
     *
     * @return número entero de horas.
     */
    public int getIntensidadHoraria() {
        return intensidadHoraria;
    }

    /**
     * Define la intensidad horaria semanal.
     *
     * @param intensidadHoraria número de horas.
     */
    public void setIntensidadHoraria(int intensidadHoraria) {
        this.intensidadHoraria = intensidadHoraria;
    }
}
