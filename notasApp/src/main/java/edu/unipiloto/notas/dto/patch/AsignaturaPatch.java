package edu.unipiloto.notas.dto.patch;

/**
 * DTO de actualización parcial (PATCH) para la entidad {@code Asignatura}.
 *
 * <p>
 * Esta clase encapsula los campos opcionales que pueden ser modificados durante
 * una operación PATCH. Solo los valores no nulos enviados por el cliente serán
 * aplicados en el proceso de actualización parcial.
 * </p>
 *
 * <p>
 * Campos soportados para actualización parcial:
 * <ul>
 *   <li>{@code docenteId}: Identificador del docente asociado.</li>
 *   <li>{@code nombre}: Nombre de la asignatura.</li>
 *   <li>{@code intensidadHoraria}: Intensidad horaria semanal.</li>
 * </ul>
 * </p>
 */
public class AsignaturaPatch {

    /** Identificador del docente asociado (opcional). */
    private Long docenteId;

    /** Nombre de la asignatura (opcional). */
    private String nombre;

    /** Intensidad horaria semanal (opcional). */
    private Integer intensidadHoraria;

    /**
     * Retorna el identificador del docente a actualizar.
     *
     * @return ID del docente o {@code null} si no cambia
     */
    public Long getDocenteId() {
        return docenteId;
    }

    /**
     * Establece el identificador del docente para esta actualización parcial.
     *
     * @param docenteId nuevo ID o {@code null} para omitir cambio
     */
    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    /**
     * Retorna el nombre de la asignatura a actualizar.
     *
     * @return nombre o {@code null} si no cambia
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la asignatura para esta actualización parcial.
     *
     * @param nombre nuevo nombre o {@code null} para omitir cambio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la intensidad horaria a actualizar.
     *
     * @return intensidad horaria o {@code null} si no cambia
     */
    public Integer getIntensidadHoraria() {
        return intensidadHoraria;
    }

    /**
     * Establece la intensidad horaria para esta actualización parcial.
     *
     * @param intensidadHoraria valor nuevo o {@code null} para omitir cambio
     */
    public void setIntensidadHoraria(Integer intensidadHoraria) {
        this.intensidadHoraria = intensidadHoraria;
    }
}
