package edu.unipiloto.notas.model;

public class Asignatura {
  private int id;
  private int docenteId; 
  private String nombre;
  private int intensidadHoraria;

	public Asignatura() {
	}

	public Asignatura(int id, int docenteId, String nombre, int intensidadHoraria) {
		this.id = id;
		this.docenteId = docenteId;
		this.nombre = nombre;
		this.intensidadHoraria = intensidadHoraria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDocenteId() {
		return docenteId;
	}

	public void setDocenteId(int docenteId) {
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