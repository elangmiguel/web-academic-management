package edu.unipiloto.notas.dto.response;

import edu.unipiloto.notas.dto.response.template.BaseResponseDTO;

public class AsignaturaResponse extends BaseResponseDTO {
	private Long docenteId;
	private String nombre;
	private int intensidadHoraria;

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

	public int getIntensidadHoraria() {
		return intensidadHoraria;
	}

	public void setIntensidadHoraria(int intensidadHoraria) {
		this.intensidadHoraria = intensidadHoraria;
	}

}