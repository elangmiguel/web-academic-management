# D

Desarrollo de una aplicacion web de gestion de notas guiado por el documento: *TALLER PRÁCTICO: Desarrollo de una Aplicación Web de Notas con Maven*

## Inicializacion del proyecto

Utilizando el comando dado en la guia de trabajo para la inicializacion del proyecto

```sh
mvn archetype:generate -DgroupId=edu.unipiloto.notas -DartifactId=notasApp -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

Se obtienen multiples errores señalando que el metodo utilizado es obsoleto y muchas de las practicas utilizadas en el documento no son recomendadas o directamente son incompatibles para el desarrollo.

```sh
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/C:/Maven/mvn/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
```

Se evidencia aplicacion de todos los parametros dados al proyecto.

```sh
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Old (1.x) Archetype: maven-archetype-webapp:1.0
[INFO] ----------------------------------------------------------------------------     
[INFO] Parameter: basedir, Value: C:\Users\elang\web-academic-management
[INFO] Parameter: package, Value: edu.unipiloto.notas
[INFO] Parameter: groupId, Value: edu.unipiloto.notas
[INFO] Parameter: artifactId, Value: notasApp
[INFO] Parameter: packageName, Value: edu.unipiloto.notas
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] project created from Old (1.x) Archetype in dir: C:\Users\elang\web-academic-management\notasApp
```

Desglosando los parametros utilizados, el comando utiliza el ejecutable de apache maven `mvn` para:

- `archetype:generate` generar un proyecto nuevo a partir de un arquetipo
- `-DgroupId=edu.unipiloto.notas` identificador de grupo del proyecto (namespace)
- `-DartifactId=notasApp` identificador del artefacto (nombre de la carpeta del proyecto y del archivo compilado)
- `-DarchetypeArtifactId=maven-archetype-webapp` arquetipo o plantilla para proyectos web
- `-DinteractiveMode=false` generacion no interactiva

La ejecucion de esta configuracion resulta en el siguiente proyecto/plantilla:

```sh
notasApp\  --- raíz del proyecto (artifactId)
│ 
├───pom.xml  --- archivo principal de configuración Maven
│   
└───src
    └───main
        ├───resources  --- oarchivos de configuracion y recursos estaticos
        └───webapp
            │   index.jsp  --- página principal
            │
            └───WEB-INF
                    web.xml  --- descriptor web

```

Posteriormente, se agregan los directorios indicados en la guia para alinear al desarrollo estimado.

```sh
notasApp/ 
│   
├───pom.xml
│   
├───src
│   └───main
│       ├───java
│       │   └───edu
│       │       └───unipiloto
│       │           └───notas  --- identificador de grupo del proyecto (namespace)
│       │               ├───controller  --- para clases que manejan la lógica de control/servlets
│       │               └───model  --- para clases de modelo de datos (POJOs)
│       ├───resources
│       └───webapp
│           │   index.jsp
│           │
│           ├───vistas  --- para páginas JSP adicionales
│           └───WEB-INF
│                   web.xml
│
└───test  --- para tests unitarios
```

## Dependencias

Se añaden las dependencias indicadas para el desarrollo. JUnit se añadio al crear el proyecto:

- `junit` Proporciona un framework para crear y ejecutar pruebas unitarias en Java.
- `servlet-api` Proporciona las clases necesarias para crear Servlets, que son la base de aplicaciones web Java.
- `mysql-connector-java` Driver JDBC para conectar la aplicacion con bases de datos MySQL.
- `servlet.jsp-api` Proporciona las clases necesarias para trabajar con JSP (JavaServer Pages). Esto permite generar contenido HTML dinámico en el servidor.
- `apache-commons-dbcp2` Proporciona un pool de conexiones JDBC.

`javax.servlet` y `javax.servlet.jsp` se marcan con el scope provided, ya que Apache Tomcat, el servidor de aplicaciones, las proporciona en tiempo de ejecucion. Por lo tantono se necesita incluirlas físicamente en el despliegue.

## Desarrollo

### Persistencia - Conexion a la base de datos

Utilizando el contenido proporcionado.

```java
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
```

Procurando seguir los lineamientos dados, se crea la base de datos con base en los parametros usados, sea: La base de datos notasdb MySQL en el puerto predeterminado y en el host local, accedida por el usuario root sin contraseña.

```java
  URL = "jdbc:mysql://localhost:3306/notasdb";
  USER = "root";
  PASS = "";
```

### MVC

De aqui en adelante se da libertad de desarrollo con los siguientes objetivos.

1. Crear clases **modelo**: Estudiante.java, Nota.java, etc.
2. Crear **DAOs** para acceso a datos (ej. EstudianteDAO.java).
3. Implementar **servlets** para manejar las peticiones (ej. EstudianteServlet.java).
4. Crear **vistas JSP** para registrar y listar los datos.

Ademas, se enfatiza en la practica de la documentacion con JavaDocs.

#### Modelos

```SQL
Estudiante (
  id, 
  nombres, 
  apellidos, 
  documento, 
  correo, 
  ciclo
) 

Docente (
  id, 
  nombres, 
  apellidos, 
  correo
  )

Asignatura (
  id, 
  docente_id,
  nombre, 
  intensidad_horaria
  )

Nota (
  id, 
  estudiante_id, 
  asignatura_id, 
  nota1, 
  nota2, 
  nota_final, 
  observaciones
  ) 
```
