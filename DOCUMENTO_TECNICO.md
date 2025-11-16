# Sistema de Gestión Académica

## Descripcion del proyecto

El Sistema de Gestión Académica es una aplicación desarrollada en Java utilizando Springboot, cuyo objetivo es facilitar la administración de información academica.

La aplicacion permite:

- Registrar estudiantes, asignaturas y docentes.
- Registrar notas por estudiante y asignatura.
- Consultar y modificar registros.
- Generar un reporte de promedios por curso o ciclo.

El sistema utiliza MySQL como base de datos y se ejecuta en un servidor Apache Tomcat, empaquetado como archivo WAR mediante Maven.

## Arquitectura del sistema

El sistema sigue una arquitectura por capas:

```sh
Tomcat (Servidor Web)
        ↓
Vistas JSP  (Interfaz de usuario)
        ↓
Servlets (Controladores)
        ↓
DAO (Acceso a datos)
        ↓
DTO (Objetos de transferencia)
        ↓
Mapper (Conversión DTO ↔ Entity)
        ↓
Entity (Modelo persistente)
```

### Descripción de capas

- **Vistas JSP:** generan las interfaces para registrar estudiantes, notas y consultar promedios.
- **Servlets:** reciben solicitudes desde las vistas y coordinan la lógica necesaria.
- **DAO:** ejecuta las operaciones JDBC sobre la base de datos.
- **DTO:** objetos usados para transportar datos entre las vistas y entidades del sistema.
- **Mapper:** convierte entre DTO y Entity para mantener separación de responsabilidades.
- **Entity:** representa cada tabla de la base de datos.

## Diagrama Entidad-Relación

### Tablas principales

- Estudiante
  - nombres
  - apellidos
  - documento
  - correo
  - ciclo
- Docente
  - nombres
  - apellidos
  - correo
- Asignatura
  - docente_id
  - nombre
  - intensidad_horaria
- Nota
  - estudiante_id
  - asignatura_id
  - nota1
  - nota2
  - nota_final
  - observaciones

### Relaciones

- Un docente dicta muchas asignaturas 1:N
- Un estudiante tiene muchas notas 1:N
- Una asignatura tiene muchas notas 1:N

## Estructura de Paquetes del proyecto

```sh
C:.
│   pom.xml
├───src/main
│       ├───java/edu/unipiloto/notas
│       │               ├───config
│       │               │       ConnectionManager.java
│       │               ├───dao
│       │               ├───dto
│       │               │   ├───patch
│       │               │   ├───request
│       │               │   └───response
│       │               ├───mapper
│       │               │   └───factory
│       │               ├───model
│       │               └───servlet
│       ├───resources
│       └───webapp
│           │   index.jsp
│           ├───estilos
│           ├───vistas
│           └───WEB-INF
│                   web.xml
└───test
```

## Dependencias Usadas

### Dependencias asignadas

- JUnit 3.8.1 – pruebas unitarias
- Servlet API 4.0.1 – controladores HTTP
- JSP API 2.3.3 – vistas JSP
- MySQL Connector 8.0.33 – conexión a la BD
- Apache Commons DBCP2 – pool de conexiones

### Dependencias añadidas

- Gson 2.13.2 – gestion de JSON, necesaria para facilitar la convesion json/application
- Maven WAR Plugin – empaquetado WARx

## Ejemplo de configuracion del Pom.xml (Resumido)

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>edu.unipiloto.notas</groupId>
    <artifactId>notasApp</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-dbcp2</artifactId>
            <version>2.9.0</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.3</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.13.2</version>
        </dependency>
    </dependencies>

    <build>
        <finalName>notasApp</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.2</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
