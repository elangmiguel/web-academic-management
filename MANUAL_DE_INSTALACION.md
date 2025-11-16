# MANUAL DE INSTALACIÓN

Este documento describe los pasos necesarios para instalar, configurar y ejecutar el Sistema de Gestión Académica a partir del codigo fuente.

## Requisitos Minimos

### Hardware (?)

### Software

- Java JDK 9 o superior
- Apache Tomcat 9
- MySQL Server 8+
- Maven 3+
- IDE VSCode / Eclipse / IntelliJ / NetBeans

## Instalación

### Importar el proyecto al IDE

#### En IDEs especificos

- Abrir el IDE.
- Seleccionar File → Import → Maven → Existing Maven Project.
- Seleccionar la carpeta del proyecto notasApp.
- Maven descargará las dependencias automáticamente.

#### En VSCode

- Abrir el IDE.
- Seleccionar File → open folder.
- Seleccionar la carpeta del proyecto notasApp.
- Con la extension `Maven for Java` identificara automaticamente el proyecto.

### Configurar la base de datos

Se proporciona el script completo con registros de prueba en [./resources/sql/script.sql](./resources/sql/script.sql)

```SQL
-- Base de datos
DROP DATABASE IF EXISTS notasdb;
CREATE DATABASE IF NOT EXISTS notasdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE notasdb;

-- Tabla Estudiante
CREATE TABLE IF NOT EXISTS estudiante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    documento VARCHAR(50) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    ciclo INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla Docente
CREATE TABLE IF NOT EXISTS docente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla Asignatura
CREATE TABLE IF NOT EXISTS asignatura (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    docente_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    intensidad_horaria INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (docente_id) REFERENCES docente(id) ON DELETE CASCADE
);

-- Tabla Nota
CREATE TABLE IF NOT EXISTS nota (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    asignatura_id BIGINT NOT NULL,
    nota1 DECIMAL(5,2),
    nota2 DECIMAL(5,2),
    nota_final DECIMAL(5,2),
    observaciones VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id) ON DELETE CASCADE,
    FOREIGN KEY (asignatura_id) REFERENCES asignatura(id) ON DELETE CASCADE
);
```

### Configurar la conexión a la base de datos

En el archivo de configuración `notasApp\src\main\resources\db.properties`

```ini
db.url=jdbc:mysql://localhost:3306/notasdb?useSSL=false&serverTimezone=UTC
db.username=root
db.password=
```

### Ejecutar el sistema en Tomcat

Descarga Tomcat en `C:\Program Files\Apache Tomcat` y ejecuta `notasApp\mvn-package.bat` para compilar el proyecto al directorio webapps de Tomcat. (Editar la direccion del mvn-package.bat de cambiar la ubicacion de la instalacion de Tomcat.)

Ejecuta el servidor de Tomcat `C:\Program Files\Apache Tomcat\bin\startup.bat`

Accede al servidor [http://localhost:8080/notasApp/](http://localhost:8080/notasApp/)
