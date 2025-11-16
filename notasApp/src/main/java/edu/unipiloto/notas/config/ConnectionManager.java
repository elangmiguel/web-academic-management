package edu.unipiloto.notas.config;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase de configuración y manejo de conexiones a la base de datos MySQL.
 * Utiliza Apache DBCP2 para el pool de conexiones.
 */
public class ConnectionManager {
	@SuppressWarnings("resource")
	private static final BasicDataSource dataSource = new BasicDataSource();

	static {
		try {
			// Cargar driver MySQL explícitamente
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("MySQL JDBC Driver no encontrado en el classpath.", e);
		}

		// Configuración del pool de conexiones
		dataSource.setUrl("jdbc:mysql://localhost:3306/notasdb?useSSL=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("");

		// Pool de conexiones
		dataSource.setInitialSize(5); // conexiones iniciales
		dataSource.setMaxTotal(20); // máximo de conexiones activas
		dataSource.setMaxIdle(10); // máximo de conexiones inactivas
		dataSource.setMinIdle(5); // mínimo de conexiones inactivas
		dataSource.setValidationQuery("SELECT 1"); // para validar conexiones activas
		dataSource.setTestOnBorrow(true); // validar al obtener del pool
	}

	/**
	 * Obtiene el DataSource del pool de conexiones.
	 *
	 * @return DataSource configurado
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Obtiene una conexión de la base de datos.
	 *
	 * @return Conexión activa
	 * @throws SQLException si no se puede obtener la conexión
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * Método de prueba rápida para verificar la conexión.
	 */
	public static void testConnection() {
		try (Connection conn = getConnection()) {
			System.out.println("Conexión exitosa: " + conn.getMetaData().getDatabaseProductName() +
					" " + conn.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al conectarse a la base de datos.", e);
		}
	}
}
