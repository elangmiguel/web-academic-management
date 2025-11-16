<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../estilos/styles.css">
    <title>Notas</title>
    <script>
        // Carga todas las notas desde el servlet GenericCrudServlet
		async function cargarNotas() {
			try {
				const resp = await fetch('../notas/');
				if (!resp.ok) {
					alert('Error al cargar notas: ' + resp.statusText);
					return;
				}
				const data = await resp.json();
				console.log("Datos recibidos:", data);

				const tbody = document.getElementById("notasBody");
				if (!tbody) {
					console.error("No se encontró el tbody");
					return;
				}
				tbody.innerHTML = ""; // limpiar contenido previo

		data.forEach(n => {
			const tr = document.createElement("tr");

			const tdId = document.createElement("td"); tdId.textContent = n.id; tr.appendChild(tdId);
			const tdEst = document.createElement("td"); tdEst.textContent = n.estudianteId; tr.appendChild(tdEst);
			const tdAsig = document.createElement("td"); tdAsig.textContent = n.asignaturaId; tr.appendChild(tdAsig);
			const td1 = document.createElement("td"); td1.textContent = n.nota1; tr.appendChild(td1);
			const td2 = document.createElement("td"); td2.textContent = n.nota2; tr.appendChild(td2);
			const tdFinal = document.createElement("td"); tdFinal.textContent = n.notaFinal; tr.appendChild(tdFinal);
			const tdObs = document.createElement("td"); tdObs.textContent = n.observaciones || "false"; tr.appendChild(tdObs);

			tbody.appendChild(tr);
		});

			} catch (e) {
				console.error("Error cargando notas:", e);
			}
		}

        async function agregarNota() {
            const body = {
                estudianteId: parseInt(document.getElementById("estudianteId").value),
                asignaturaId: parseInt(document.getElementById("asignaturaId").value),
                nota1: parseFloat(document.getElementById("nota1").value),
                nota2: parseFloat(document.getElementById("nota2").value),
                notaFinal: parseFloat(document.getElementById("notaFinal").value),
                observaciones: document.getElementById("observaciones").value
            };

            const resp = await fetch('../notas/', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(body)
            });

            if (!resp.ok) {
                const errorText = await resp.text();
                alert('Error al agregar nota: ' + errorText);
                return;
            }

            document.getElementById("notaForm").reset();
            cargarNotas();
        }

        window.onload = cargarNotas;
    </script>
</head>
<body>
<h1>Notas</h1>

<form id="notaForm" onsubmit="event.preventDefault(); agregarNota();">
    Estudiante ID: <input type="number" id="estudianteId" required><br>
    Asignatura ID: <input type="number" id="asignaturaId" required><br>
    Nota 1: <input type="number" step="0.01" id="nota1" required><br>
    Nota 2: <input type="number" step="0.01" id="nota2" required><br>
    Nota Final: <input type="number" step="0.01" id="notaFinal" required><br>
    Observaciones: <input type="text" id="observaciones"><br>
    <button type="submit">Agregar</button>
</form>

<h2>Lista de Notas</h2>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Estudiante ID</th>
            <th>Asignatura ID</th>
            <th>Nota 1</th>
            <th>Nota 2</th>
            <th>Nota Final</th>
            <th>Observaciones</th>
        </tr>
    </thead>
    <tbody id="notasBody"></tbody>
</table>
</body>
</html>
