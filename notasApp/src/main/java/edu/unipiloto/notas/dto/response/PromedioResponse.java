package edu.unipiloto.notas.dto.response;

/**
 * DTO de respuesta que representa el promedio académico de un estudiante
 * en una asignatura específica.
 *
 * <p>Este objeto se utiliza para retornar al cliente información consolidada
 * del rendimiento académico, incluyendo el nombre del estudiante, el nombre
 * de la asignatura y el valor numérico del promedio calculado.</p>
 */
public class PromedioResponse {

    /** Nombre completo o identificador representativo del estudiante. */
    private String estudiante;

    /** Nombre de la asignatura evaluada. */
    private String asignatura;

    /** Valor numérico del promedio final calculado. */
    private Double promedio;

    /**
     * Obtiene el nombre del estudiante asociado al promedio.
     *
     * @return nombre del estudiante
     */
    public String getEstudiante() {
        return estudiante;
    }

    /**
     * Establece el nombre del estudiante asociado al promedio.
     *
     * @param estudiante nombre del estudiante
     */
    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * Obtiene el nombre de la asignatura evaluada.
     *
     * @return nombre de la asignatura
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * Establece el nombre de la asignatura evaluada.
     *
     * @param asignatura nombre de la asignatura
     */
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Obtiene el promedio calculado para la asignatura.
     *
     * @return promedio numérico
     */
    public Double getPromedio() {
        return promedio;
    }

    /**
     * Establece el valor numérico del promedio calculado.
     *
     * @param promedio valor del promedio
     */
    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
}
