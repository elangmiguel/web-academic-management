package edu.unipiloto.notas.dto.patch;

public class AsignaturaPatch {
	private Long docenteId;
	private String nombre;
	private Integer intensidadHoraria;

	public Long getDocenteId() {
		return docenteId;
	}

	public void setDocenteId(Long docenteId) {
		this.docenteId = docenteId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIntensidadHoraria() {
		return intensidadHoraria;
	}

	public void setIntensidadHoraria(Integer intensidadHoraria) {
		this.intensidadHoraria = intensidadHoraria;
	}

}