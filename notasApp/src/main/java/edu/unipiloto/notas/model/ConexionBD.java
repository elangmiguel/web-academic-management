package edu.unipiloto.notas.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
	private static final String URL = "jdbc:mysql://localhost:3306/notasdb";
	private static final String USER = "root";
	private static final String PASS = "";

	public static Connection conectar() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conexión exitosa a la base de datos");
		} catch (Exception e) {
			System.out.println("Error en la conexión: " + e.getMessage());
		}
		return con;
	}
}
