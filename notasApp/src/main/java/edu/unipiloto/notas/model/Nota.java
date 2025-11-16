package edu.unipiloto.notas.model;

import edu.unipiloto.notas.model.template.BaseModel;

/**
 * Modelo que representa el registro de notas asociadas a un estudiante
 * y una asignatura dentro del sistema académico.
 *
 * <p>Incluye las calificaciones parciales, la nota final calculada y
 * observaciones adicionales. Hereda los campos de auditoría definidos
 * en {@link BaseModel}.</p>
 */
public class Nota extends BaseModel {

    /**
     * Identificador del estudiante al que pertenece la nota.
     */
    private Long estudianteId;

    /**
     * Identificador de la asignatura evaluada.
     */
    private Long asignaturaId;

    /**
     * Primera calificación parcial.
     */
    private double nota1;

    /**
     * Segunda calificación parcial.
     */
    private double nota2;

    /**
     * Calificación final del estudiante en la asignatura.
     */
    private double notaFinal;

    /**
     * Observaciones relacionadas con el desempeño o la evaluación.
     */
    private String observaciones;

    /**
     * Constructor por defecto.
     * Permite crear instancias vacías para inicialización posterior.
     */
    public Nota() {
    }

    /**
     * Obtiene el identificador del estudiante.
     *
     * @return identificador del estudiante
     */
    public Long getEstudianteId() {
        return estudianteId;
    }

    /**
     * Establece el identificador del estudiante.
     *
     * @param estudianteId identificador válido del estudiante
     */
    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    /**
     * Obtiene el identificador de la asignatura.
     *
     * @return identificador de la asignatura
     */
    public Long getAsignaturaId() {
        return asignaturaId;
    }

    /**
     * Establece el identificador de la asignatura.
     *
     * @param asignaturaId identificador válido de la asignatura
     */
    public void setAsignaturaId(Long asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    /**
     * Obtiene la primera calificación parcial.
     *
     * @return valor numérico de la primera nota
     */
    public double getNota1() {
        return nota1;
    }

    /**
     * Establece la primera calificación parcial.
     *
     * @param nota1 valor numérico de la primera nota
     */
    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    /**
     * Obtiene la segunda calificación parcial.
     *
     * @return valor numérico de la segunda nota
     */
    public double getNota2() {
        return nota2;
    }

    /**
     * Establece la segunda calificación parcial.
     *
     * @param nota2 valor numérico de la segunda nota
     */
    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    /**
     * Obtiene la calificación final.
     *
     * @return nota final de la asignatura
     */
    public double getNotaFinal() {
        return notaFinal;
    }

    /**
     * Establece la calificación final.
     *
     * @param notaFinal valor numérico de la calificación final
     */
    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    /**
     * Obtiene las observaciones registradas.
     *
     * @return observaciones de la evaluación
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones asociadas a la evaluación.
     *
     * @param observaciones comentarios adicionales
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
