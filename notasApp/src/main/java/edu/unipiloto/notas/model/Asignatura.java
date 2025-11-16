package edu.unipiloto.notas.model;

import edu.unipiloto.notas.model.template.BaseModel;

/**
 * Modelo que representa una asignatura dentro del sistema académico.
 * 
 * <p>Incluye la referencia al docente responsable, el nombre oficial de la
 * asignatura y la intensidad horaria semanal.</p>
 */
public class Asignatura extends BaseModel {

    /**
     * Identificador del docente que dicta la asignatura.
     */
    private Long docenteId;

    /**
     * Nombre oficial de la asignatura.
     */
    private String nombre;

    /**
     * Intensidad horaria semanal expresada en número de horas.
     */
    private Integer intensidadHoraria;

    /**
     * Constructor por defecto.
     * Permite la creación de instancias vacías para su posterior
     * inicialización mediante setters.
     */
    public Asignatura() {
    }

    /**
     * Obtiene el identificador del docente asociado.
     *
     * @return id del docente
     */
    public Long getDocenteId() {
        return docenteId;
    }

    /**
     * Establece el identificador del docente asociado.
     *
     * @param docenteId id del docente
     */
    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    /**
     * Obtiene el nombre de la asignatura.
     *
     * @return nombre oficial
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre oficial de la asignatura.
     *
     * @param nombre nombre oficial
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la intensidad horaria semanal de la asignatura.
     *
     * @return cantidad de horas por semana
     */
    public Integer getIntensidadHoraria() {
        return intensidadHoraria;
    }

    /**
     * Establece la intensidad horaria semanal.
     *
     * @param intensidadHoraria número de horas por semana
     */
    public void setIntensidadHoraria(Integer intensidadHoraria) {
        this.intensidadHoraria = intensidadHoraria;
    }
}
