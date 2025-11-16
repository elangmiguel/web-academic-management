<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../estilos/styles.css">
    <title>Estudiantes</title>
    <script>
        // Carga todos los estudiantes
		async function cargarEstudiantes() {
			const resp = await fetch('../estudiantes/');
			if (!resp.ok) {
				alert('Error al cargar estudiantes: ' + resp.statusText);
				return;
			}

			const dataRaw = await resp.json();
			const data = Array.isArray(dataRaw) ? dataRaw : [dataRaw];

			const tbody = document.getElementById("estudiantesBody");
			tbody.innerHTML = "";

			data.forEach(e => {
				const tr = document.createElement("tr");

				const tdId = document.createElement("td");
				tdId.textContent = e.id;
				tr.appendChild(tdId);

				const tdNombres = document.createElement("td");
				tdNombres.textContent = e.nombres;
				tr.appendChild(tdNombres);

				const tdApellidos = document.createElement("td");
				tdApellidos.textContent = e.apellidos;
				tr.appendChild(tdApellidos);

				const tdDocumento = document.createElement("td");
				tdDocumento.textContent = e.documento;
				tr.appendChild(tdDocumento);

				const tdCorreo = document.createElement("td");
				tdCorreo.textContent = e.correo;
				tr.appendChild(tdCorreo);

				const tdCiclo = document.createElement("td");
				tdCiclo.textContent = e.ciclo;
				tr.appendChild(tdCiclo);

				tbody.appendChild(tr);
			});
		}

        // Agrega un estudiante usando POST
        async function agregarEstudiante() {
            const nombres = document.getElementById("nombres").value;
            const apellidos = document.getElementById("apellidos").value;
            const documento = document.getElementById("documento").value;
            const correo = document.getElementById("correo").value;
            const ciclo = document.getElementById("ciclo").value;

            const resp = await fetch('../estudiantes/', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ nombres, apellidos, documento, correo, ciclo })
            });

            if (!resp.ok) {
                const errorText = await resp.text();
                alert('Error al agregar estudiante: ' + errorText);
                return;
            }

            document.getElementById("estudianteForm").reset();
            cargarEstudiantes();
        }

        window.onload = cargarEstudiantes;
    </script>
</head>
<body>
    <h1>Estudiantes</h1>

    <form id="estudianteForm" onsubmit="event.preventDefault(); agregarEstudiante();">
        Nombres: <input type="text" id="nombres" required><br>
        Apellidos: <input type="text" id="apellidos" required><br>
        Documento: <input type="text" id="documento" required><br>
        Correo: <input type="email" id="correo" required><br>
        Ciclo: <input type="text" id="ciclo" required><br>
        <button type="submit">Agregar</button>
    </form>

    <h2>Lista de Estudiantes</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Documento</th>
                <th>Correo</th>
                <th>Ciclo</th>
            </tr>
        </thead>
        <tbody id="estudiantesBody"></tbody>
    </table>
</body>
</html>
