package edu.unipiloto.notas.dto.response;

import edu.unipiloto.notas.dto.response.template.BaseResponseDTO;

/**
 * DTO de respuesta para la entidad {@code Nota}.
 *
 * <p>Extiende {@link BaseResponseDTO} para incluir atributos comunes 
 * como el identificador, la fecha de creación y la fecha de actualización.
 * Este DTO representa los datos retornados al cliente tras operaciones
 * de consulta relacionadas con las notas académicas.</p>
 */
public class NotaResponse extends BaseResponseDTO {

    /** Identificador del estudiante al que pertenece la nota. */
    private Long estudianteId;

    /** Identificador de la asignatura asociada a la nota. */
    private Long asignaturaId;

    /** Valor numérico de la primera evaluación. */
    private double nota1;

    /** Valor numérico de la segunda evaluación. */
    private double nota2;

    /** Valor final calculado a partir de las evaluaciones registradas. */
    private double notaFinal;

    /** Observaciones adicionales sobre el desempeño o el registro de la nota. */
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
     * Obtiene la primera nota registrada.
     *
     * @return valor de la primera nota
     */
    public double getNota1() {
        return nota1;
    }

    /**
     * Establece la primera nota registrada.
     *
     * @param nota1 valor numérico de la nota
     */
    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    /**
     * Obtiene la segunda nota registrada.
     *
     * @return valor de la segunda nota
     */
    public double getNota2() {
        return nota2;
    }

    /**
     * Establece la segunda nota registrada.
     *
     * @param nota2 valor numérico de la nota
     */
    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    /**
     * Obtiene la nota final calculada.
     *
     * @return valor de la nota final
     */
    public double getNotaFinal() {
        return notaFinal;
    }

    /**
     * Establece la nota final calculada.
     *
     * @param notaFinal valor numérico de la nota final
     */
    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    /**
     * Obtiene las observaciones adicionales del registro.
     *
     * @return texto de observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece observaciones adicionales respecto a la nota.
     *
     * @param observaciones texto descriptivo
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
