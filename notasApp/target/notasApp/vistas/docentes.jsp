<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../estilos/styles.css">
    <title>Docentes</title>
    <script>
        // Carga todos los docentes
		async function cargarDocentes() {
			const resp = await fetch('../docentes/');
			if (!resp.ok) {
				alert('Error al obtener docentes: ' + resp.statusText);
				return;
			}

			const dataRaw = await resp.json();
			const data = Array.isArray(dataRaw) ? dataRaw : [dataRaw];

			const tbody = document.getElementById("docentesBody");
			tbody.innerHTML = "";

			data.forEach(d => {
				const tr = document.createElement("tr");

				const tdId = document.createElement("td");
				tdId.textContent = d.id;
				tr.appendChild(tdId);

				const tdNombres = document.createElement("td");
				tdNombres.textContent = d.nombres;
				tr.appendChild(tdNombres);

				const tdApellidos = document.createElement("td");
				tdApellidos.textContent = d.apellidos;
				tr.appendChild(tdApellidos);

				const tdCorreo = document.createElement("td");
				tdCorreo.textContent = d.correo;
				tr.appendChild(tdCorreo);

				tbody.appendChild(tr);
			});
		}

        // Agrega un docente usando POST
        async function agregarDocente() {
            const nombres = document.getElementById("nombres").value;
            const apellidos = document.getElementById("apellidos").value;
            const correo = document.getElementById("correo").value;

            const resp = await fetch('../docentes/', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ nombres, apellidos, correo })
            });

            if (!resp.ok) {
                const errorText = await resp.text();
                alert('Error al agregar docente: ' + errorText);
                return;
            }

            document.getElementById("docenteForm").reset();
            cargarDocentes();
        }

        window.onload = cargarDocentes;
    </script>
</head>
<body>
    <h1>Docentes</h1>

    <form id="docenteForm" onsubmit="event.preventDefault(); agregarDocente();">
        Nombres: <input type="text" id="nombres" required><br>
        Apellidos: <input type="text" id="apellidos" required><br>
        Correo: <input type="email" id="correo" required><br>
        <button type="submit">Agregar</button>
    </form>

    <h2>Lista de Docentes</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Correo</th>
            </tr>
        </thead>
        <tbody id="docentesBody"></tbody>
    </table>
</body>
</html>
