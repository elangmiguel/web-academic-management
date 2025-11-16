package edu.unipiloto.notas.dto.response;

public class PromedioResponse {
    private String estudiante;
    private String asignatura;
    private Double promedio;

	public String getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(String estudiante) {
		this.estudiante = estudiante;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public Double getPromedio() {
		return promedio;
	}
	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}    
}
