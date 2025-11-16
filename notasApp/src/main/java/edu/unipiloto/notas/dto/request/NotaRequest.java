package edu.unipiloto.notas.dto.request;

/**
 * Representa la solicitud para registrar o actualizar una nota académica.
 * Incluye los datos requeridos para crear un registro asociado a un
 * estudiante y una asignatura, junto con sus calificaciones parciales
 * y finales.
 */
public class NotaRequest {

    /** Identificador del estudiante al que pertenece la nota. */
    private Long estudianteId;

    /** Identificador de la asignatura evaluada. */
    private Long asignaturaId;

    /** Primera calificación parcial. */
    private double nota1;

    /** Segunda calificación parcial. */
    private double nota2;

    /** Calificación final resultante. */
    private double notaFinal;

    /** Comentarios u observaciones adicionales sobre la nota. */
    private String observaciones;

    /**
     * Obtiene el identificador del estudiante.
     *
     * @return id del estudiante
     */
    public Long getEstudianteId() {
        return estudianteId;
    }

    /**
     * Establece el identificador del estudiante.
     *
     * @param estudianteId id del estudiante
     */
    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    /**
     * Obtiene el identificador de la asignatura.
     *
     * @return id de la asignatura
     */
    public Long getAsignaturaId() {
        return asignaturaId;
    }

    /**
     * Establece el identificador de la asignatura.
     *
     * @param asignaturaId id de la asignatura
     */
    public void setAsignaturaId(Long asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    /**
     * Obtiene la primera calificación parcial.
     *
     * @return nota 1
     */
    public double getNota1() {
        return nota1;
    }

    /**
     * Establece la primera calificación parcial.
     *
     * @param nota1 valor de la calificación parcial
     */
    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    /**
     * Obtiene la segunda calificación parcial.
     *
     * @return nota 2
     */
    public double getNota2() {
        return nota2;
    }

    /**
     * Establece la segunda calificación parcial.
     *
     * @param nota2 valor de la calificación parcial
     */
    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    /**
     * Obtiene la calificación final.
     *
     * @return nota final
     */
    public double getNotaFinal() {
        return notaFinal;
    }

    /**
     * Establece la calificación final.
     *
     * @param notaFinal valor de la nota final
     */
    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    /**
     * Obtiene las observaciones asociadas a la evaluación.
     *
     * @return texto de observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece observaciones adicionales sobre la nota.
     *
     * @param observaciones comentarios o anotaciones adicionales
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
