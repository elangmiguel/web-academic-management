package edu.unipiloto.notas.model;

import edu.unipiloto.notas.model.template.BaseModel;

/**
 * Modelo que representa una asignatura dentro del sistema académico.
 */
public class Asignatura extends BaseModel {
	private Long docenteId;
	private String nombre;
	private Integer intensidadHoraria;

	public Asignatura() {
	}

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
