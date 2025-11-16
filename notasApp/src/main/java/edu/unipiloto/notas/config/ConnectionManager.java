package edu.unipiloto.notas.config;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase de configuración y manejo de conexiones a la base de datos MySQL.
 * Utiliza Apache DBCP2 para el pool de conexiones, cargando propiedades desde un archivo externo.
 */
public class ConnectionManager {
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        Properties props = new Properties();
        try (InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Archivo de propiedades db.properties no encontrado en classpath.");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar db.properties.", e);
        }

        try {
            Class.forName(props.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver no encontrado en el classpath.", e);
        }

        dataSource.setUrl(props.getProperty("db.url"));
        dataSource.setUsername(props.getProperty("db.username"));
        dataSource.setPassword(props.getProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(props.getProperty("db.initialSize")));
        dataSource.setMaxTotal(Integer.parseInt(props.getProperty("db.maxTotal")));
        dataSource.setMaxIdle(Integer.parseInt(props.getProperty("db.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(props.getProperty("db.minIdle")));
        dataSource.setValidationQuery(props.getProperty("db.validationQuery"));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(props.getProperty("db.testOnBorrow")));
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

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
