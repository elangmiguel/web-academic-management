package edu.unipiloto.notas.model;

/**
 * Representa un estudiante en el sistema académico.
 */
public class Estudiante {
    private int id;
    private String nombres;
    private String apellidos;
    private String documento;
    private String correo;
    private String ciclo;
		
		public Estudiante() {
		}

		public Estudiante(int id, String nombres, String apellidos, String documento, String correo, String ciclo) {
			this.id = id;
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.documento = documento;
			this.correo = correo;
			this.ciclo = ciclo;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombres() {
			return nombres;
		}

		public void setNombres(String nombres) {
			this.nombres = nombres;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public String getDocumento() {
			return documento;
		}

		public void setDocumento(String documento) {
			this.documento = documento;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public String getCiclo() {
			return ciclo;
		}

		public void setCiclo(String ciclo) {
			this.ciclo = ciclo;
		}

		
}
