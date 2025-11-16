package edu.unipiloto.notas.dto.patch;

/**
 * DTO de actualización parcial (PATCH) para la entidad {@code Nota}.
 *
 * <p>
 * Representa los campos opcionales que pueden ser modificados durante una
 * operación parcial. Solo los valores no nulos enviados por el cliente
 * serán aplicados en el proceso de actualización.
 * </p>
 *
 * <p>Campos admitidos para actualización parcial:</p>
 * <ul>
 *   <li>{@code estudianteId}: Identificador del estudiante.</li>
 *   <li>{@code asignaturaId}: Identificador de la asignatura.</li>
 *   <li>{@code nota1}: Nota parcial 1.</li>
 *   <li>{@code nota2}: Nota parcial 2.</li>
 *   <li>{@code notaFinal}: Nota final.</li>
 *   <li>{@code observaciones}: Comentarios u observaciones adicionales.</li>
 * </ul>
 */
public class NotaPatch {

    /** Identificador del estudiante asociado (opcional). */
    private Long estudianteId;

    /** Identificador de la asignatura asociada (opcional). */
    private Long asignaturaId;

    /** Nota parcial 1 (opcional). */
    private Double nota1;

    /** Nota parcial 2 (opcional). */
    private Double nota2;

    /** Nota final (opcional). */
    private Double notaFinal;

    /** Observaciones complementarias (opcional). */
    private String observaciones;

    /**
     * Retorna el identificador del estudiante.
     *
     * @return ID del estudiante o {@code null} si no cambia
     */
    public Long getEstudianteId() {
        return estudianteId;
    }

    /**
     * Establece el identificador del estudiante.
     *
     * @param estudianteId nuevo ID o {@code null} para omitir cambio
     */
    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    /**
     * Retorna el identificador de la asignatura.
     *
     * @return ID de la asignatura o {@code null} si no cambia
     */
    public Long getAsignaturaId() {
        return asignaturaId;
    }

    /**
     * Establece el identificador de la asignatura.
     *
     * @param asignaturaId nuevo ID o {@code null} si se omite el cambio
     */
    public void setAsignaturaId(Long asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    /**
     * Retorna la primera nota parcial.
     *
     * @return nota o {@code null} si no cambia
     */
    public Double getNota1() {
        return nota1;
    }

    /**
     * Establece la primera nota parcial.
     *
     * @param nota1 nueva nota o {@code null} para omitir
     */
    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    /**
     * Retorna la segunda nota parcial.
     *
     * @return nota o {@code null} si no cambia
     */
    public Double getNota2() {
        return nota2;
    }

    /**
     * Establece la segunda nota parcial.
     *
     * @param nota2 nueva nota o {@code null} para omitir
     */
    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    /**
     * Retorna la nota final.
     *
     * @return nota final o {@code null} si no cambia
     */
    public Double getNotaFinal() {
        return notaFinal;
    }

    /**
     * Establece la nota final.
     *
     * @param notaFinal nueva nota o {@code null} para omitir
     */
    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    /**
     * Retorna las observaciones asociadas.
     *
     * @return texto de observaciones o {@code null} si no cambia
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones.
     *
     * @param observaciones texto descriptivo o {@code null} para omitir
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
