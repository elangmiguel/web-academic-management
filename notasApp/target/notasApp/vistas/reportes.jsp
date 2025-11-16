<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reporte de Promedios</title>
    <link rel="stylesheet" type="text/css" href="../estilos/styles.css">
    <script>
        async function cargarPromedios() {
            const ciclo = document.getElementById("ciclo").value;
            const curso = document.getElementById("curso").value;

            let url = "../reportes/promedios?";
            if (ciclo) url += "ciclo=" + encodeURIComponent(ciclo) + "&";
            if (curso) url += "curso=" + encodeURIComponent(curso);

            try {
                const resp = await fetch(url);
                if (!resp.ok) {
                    alert('Error al cargar promedios: ' + resp.statusText);
                    return;
                }

                const dataRaw = await resp.json();
                const data = Array.isArray(dataRaw) ? dataRaw : [dataRaw];

                const tbody = document.getElementById("promediosBody");
                tbody.innerHTML = "";

                data.forEach(item => {
                    const tr = document.createElement("tr");

                    const tdEstudiante = document.createElement("td");
                    tdEstudiante.textContent = item.estudiante;
                    tr.appendChild(tdEstudiante);

                    const tdAsignatura = document.createElement("td");
                    tdAsignatura.textContent = item.asignatura;
                    tr.appendChild(tdAsignatura);

                    const tdPromedio = document.createElement("td");
                    tdPromedio.textContent = parseFloat(item.promedio).toFixed(2);
                    tr.appendChild(tdPromedio);

                    tbody.appendChild(tr);
                });
            } catch (error) {
                alert('Error al procesar los promedios: ' + error.message);
            }
        }

        window.onload = cargarPromedios;
    </script>
</head>
<body>
    <h1>Reporte de Promedios</h1>

    <form>
        <label for="ciclo">Ciclo:</label>
        <input type="number" id="ciclo" placeholder="Ciclo (opcional)">

        <label for="curso">Curso:</label>
        <input type="text" id="curso" placeholder="Curso (opcional)">

        <button type="button" onclick="cargarPromedios()">Cargar Promedios</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>Estudiante</th>
                <th>Asignatura</th>
                <th>Promedio</th>
            </tr>
        </thead>
        <tbody id="promediosBody">
            <!-- Los promedios se cargan aquí vía JavaScript -->
        </tbody>
    </table>
</body>
</html>
