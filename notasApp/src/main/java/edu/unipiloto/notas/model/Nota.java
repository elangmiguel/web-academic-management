package edu.unipiloto.notas.model;

public class Nota {
	private int id;
	private int estudianteId; // referencia al estudiante
	private int asignaturaId; // referencia a la asignatura
	private double nota1;
	private double nota2;
	private double notaFinal;
	private String observaciones;

	public Nota() {}

	public Nota(int id, int estudianteId, int asignaturaId, double nota1, double nota2, double notaFinal,
			String observaciones) {
		this.id = id;
		this.estudianteId = estudianteId;
		this.asignaturaId = asignaturaId;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.notaFinal = notaFinal;
		this.observaciones = observaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(int estudianteId) {
		this.estudianteId = estudianteId;
	}

	public int getAsignaturaId() {
		return asignaturaId;
	}

	public void setAsignaturaId(int asignaturaId) {
		this.asignaturaId = asignaturaId;
	}

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	
}