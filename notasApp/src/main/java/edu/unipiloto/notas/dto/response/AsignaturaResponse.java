package edu.unipiloto.notas.dto.response;

import edu.unipiloto.notas.dto.response.template.BaseResponseDTO;

/**
 * DTO de respuesta para la entidad {@code Asignatura}.
 * 
 * <p>Extiende {@link BaseResponseDTO} para incluir información base
 * como identificador, fecha de creación y fecha de actualización.
 * Este DTO representa los datos retornados al cliente tras operaciones
 * de consulta sobre asignaturas.</p>
 */
public class AsignaturaResponse extends BaseResponseDTO {

    /** Identificador del docente asociado a la asignatura. */
    private Long docenteId;

    /** Nombre oficial de la asignatura. */
    private String nombre;

    /** Intensidad horaria semanal expresada en horas. */
    private int intensidadHoraria;

    /**
     * Obtiene el identificador del docente que dicta la asignatura.
     *
     * @return identificador del docente
     */
    public Long getDocenteId() {
        return docenteId;
    }

    /**
     * Establece el identificador del docente asociado a la asignatura.
     *
     * @param docenteId id del docente
     */
    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    /**
     * Obtiene el nombre de la asignatura.
     *
     * @return nombre de la asignatura
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la asignatura.
     *
     * @param nombre nombre oficial de la asignatura
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la intensidad horaria semanal de la asignatura.
     *
     * @return horas de intensidad horaria
     */
    public int getIntensidadHoraria() {
        return intensidadHoraria;
    }

    /**
     * Establece la intensidad horaria semanal de la asignatura.
     *
     * @param intensidadHoraria número de horas por semana
     */
    public void setIntensidadHoraria(int intensidadHoraria) {
        this.intensidadHoraria = intensidadHoraria;
    }
}
