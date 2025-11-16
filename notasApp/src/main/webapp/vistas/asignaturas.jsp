<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../estilos/styles.css">
    <title>Asignaturas</title>
    <script>
        // Carga todas las asignaturas
		async function cargarAsignaturas() {
			const resp = await fetch('../asignaturas/');
			if (!resp.ok) {
				alert('Error al obtener asignaturas: ' + resp.statusText);
				return;
			}

			const dataRaw = await resp.json();
			const data = Array.isArray(dataRaw) ? dataRaw : [dataRaw];

			const tbody = document.getElementById("asignaturasBody");
			tbody.innerHTML = "";

			data.forEach(a => {
				const tr = document.createElement("tr");

				const tdId = document.createElement("td");
				tdId.textContent = a.id;
				tr.appendChild(tdId);

				const tdNombre = document.createElement("td");
				tdNombre.textContent = a.nombre;
				tr.appendChild(tdNombre);

				const tdDocente = document.createElement("td");
				tdDocente.textContent = a.docenteId;
				tr.appendChild(tdDocente);

				const tdHoras = document.createElement("td");
				tdHoras.textContent = a.intensidadHoraria;
				tr.appendChild(tdHoras);

				tbody.appendChild(tr);
			});
		}

        // Agrega una asignatura
        async function agregarAsignatura() {
            const nombre = document.getElementById("nombre").value;
            const docenteId = parseInt(document.getElementById("docenteId").value);
            const intensidadHoraria = parseInt(document.getElementById("intensidadHoraria").value);

            const resp = await fetch('../asignaturas/', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ nombre, docenteId, intensidadHoraria })
            });

            if (!resp.ok) {
                const errorText = await resp.text();
                alert('Error al agregar asignatura: ' + errorText);
                return;
            }

            document.getElementById("asignaturaForm").reset();
            cargarAsignaturas();
        }

        window.onload = cargarAsignaturas;
    </script>
</head>
<body>
    <h1>Asignaturas</h1>

    <form id="asignaturaForm" onsubmit="event.preventDefault(); agregarAsignatura();">
        Nombre: <input type="text" id="nombre" required><br>
        Docente ID: <input type="number" id="docenteId" required><br>
        Intensidad Horaria: <input type="number" id="intensidadHoraria" required><br>
        <button type="submit">Agregar</button>
    </form>

    <h2>Lista de Asignaturas</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Docente ID</th>
                <th>Intensidad Horaria</th>
            </tr>
        </thead>
        <tbody id="asignaturasBody"></tbody>
    </table>
</body>
</html>
