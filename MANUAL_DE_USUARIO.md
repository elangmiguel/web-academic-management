# MANUAL DE USUARIO

El presente manual guía al usuario final en la instalacion, configuración y uso del Sistema de Gestión Académica, que permite registrar estudiantes, asignaturas, docentes y notas, así como consultar promedios académicos.

## Objetivo del Sistema

El Sistema de Gestión Académica permite:

- Registrar / Consultar estudiantes
- Registrar / Consultar docentes
- Registrar / Consultar asignaturas
- Registrar / Consultar notas
- Consultar promedios académicos

![vista: estudiantes](/resources/notasApp.png)

Accede al servidor [http://localhost:8080/notasApp/](http://localhost:8080/notasApp/)

## Uso de la aplicación

### Registrar estudiante

- Ingresar al módulo Estudiantes.
- Completar los campos:
  - Nombres
  - Apellidos
  - Documento
  - Correo
  - Ciclo
- Clic en `Agregar`.

![vista: estudiantes](/resources/notasApp.vistas.estudiantes.png)

---

### Registrar docente

- Ingresar al módulo Docentes.
- Completar los campos:
  - Nombres
  - Apellidos
  - Correo
- Clic en `Agregar`.

![vista: docentes](/resources/notasApp.vistas.docentes.png)

---

### Registrar asignatura

- Ingresar al módulo Asignaturas.
- Completar los campos:
  - Docente (seleccionar de lista)
  - Nombre
  - Intensidad horaria
- Clic en `Agregar`.

![vista: asignaturas](/resources/notasApp.vistas.asignaturas.png)

---

### Registrar nota

- Ingresar al módulo Notas.
- Completar los campos:
  - Estudiante (seleccionar de lista)
  - Asignatura (seleccionar de lista)
  - Nota 1
  - Nota 2
  - Nota final
  - Observaciones
- Clic en `Agregar`.

![vista: notas](/resources/notasApp.vistas.notas.png)

### Consultar reportes / promedios

- Ingresar al módulo reportes.
- (opcional) ingresa el ciclo a filtrar
- (opcional) ingresa el curso a filtrar
- Clic en `Cargar` para actualizar los resultados.

![vista: reportes](/resources/notasApp.vistas.reportes.{ciclo}.png)
