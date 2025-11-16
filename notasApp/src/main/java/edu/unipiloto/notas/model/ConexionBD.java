package edu.unipiloto.notas.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase responsable de gestionar la conexión con la base de datos MySQL.
 *
 * <p>Proporciona un método estático para establecer una conexión utilizando
 * JDBC, cargando explícitamente el driver del conector MySQL.</p>
 */
public class ConexionBD {

    /** URL de conexión hacia la base de datos. */
    private static final String URL = "jdbc:mysql://localhost:3306/notasdb";

    /** Nombre de usuario para la conexión. */
    private static final String USER = "root";

    /** Contraseña asociada al usuario de conexión. */
    private static final String PASS = "";

    /**
     * Establece una conexión con la base de datos MySQL especificada.
     *
     * @return objeto {@link Connection} si la conexión es exitosa; null en caso de error
     */
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
