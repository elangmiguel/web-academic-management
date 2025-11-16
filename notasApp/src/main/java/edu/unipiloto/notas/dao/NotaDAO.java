package edu.unipiloto.notas.dao;

import edu.unipiloto.notas.dao.template.CrudDAO;
import edu.unipiloto.notas.dto.patch.NotaPatch;
import edu.unipiloto.notas.dto.request.NotaRequest;
import edu.unipiloto.notas.dto.response.NotaResponse;
import edu.unipiloto.notas.dto.response.PromedioResponse;
import edu.unipiloto.notas.mapper.NotaMapper;
import edu.unipiloto.notas.mapper.factory.MapperFactory;
import edu.unipiloto.notas.model.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO específico para la entidad {@link Nota}.
 *
 * <p>
 * Extiende {@link CrudDAO} y define las operaciones concretas necesarias para:
 * <ul>
 * <li>Resolución del nombre de la tabla</li>
 * <li>Definición de columnas para SELECT, INSERT y UPDATE</li>
 * <li>Construcción de entidades desde {@link ResultSet}</li>
 * <li>Asignación de parámetros en sentencias preparadas</li>
 * <li>Generación dinámica de parches parciales a través de
 * {@link NotaPatch}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Utiliza {@link NotaMapper} (vía {@link MapperFactory#nota()}) para
 * convertir entre la entidad {@link Nota} y los DTO correspondientes:
 * {@link NotaRequest}, {@link NotaResponse} y {@link NotaPatch}.
 * </p>
 *
 * <p>
 * Columnas definidas:
 * <ul>
 * <li>Tabla: {@code nota}</li>
 * <li>SELECT:
 * {@code id, estudiante_id, asignatura_id, nota1, nota2, nota_final, observaciones, created_at, updated_at}</li>
 * <li>INSERT:
 * {@code estudiante_id, asignatura_id, nota1, nota2, nota_final, observaciones}</li>
 * <li>UPDATE:
 * {@code estudiante_id = ?, asignatura_id = ?, nota1 = ?, nota2 = ?, nota_final = ?, observaciones = ?}</li>
 * </ul>
 * </p>
 */
public class NotaDAO extends CrudDAO<Nota, NotaRequest, NotaResponse, NotaPatch> {

	/** Nombre de la tabla asociada. */
	private static final String TABLE_NAME = "nota";

	/** Columnas proyectadas en operaciones SELECT. */
	private static final String SELECT_COLUMNS = "id, estudiante_id, asignatura_id, nota1, nota2, nota_final, observaciones, created_at, updated_at";

	/** Columnas utilizadas en operaciones INSERT. */
	private static final String INSERT_COLUMNS = "estudiante_id, asignatura_id, nota1, nota2, nota_final, observaciones";

	/** Columnas y placeholders definidos para UPDATE. */
	private static final String UPDATE_COLUMNS = "estudiante_id = ?, asignatura_id = ?, nota1 = ?, nota2 = ?, nota_final = ?, observaciones = ?";

	/** Mapper utilizado para construir entidades y DTOs. */
	private final NotaMapper mapper = MapperFactory.nota();

	/**
	 * Retorna el nombre de la tabla asociada.
	 *
	 * @return Nombre de la tabla "nota".
	 */
	@Override
	protected String tableName() {
		return TABLE_NAME;
	}

	/**
	 * Retorna el mapper encargado de procesar todas las conversiones
	 * entre entidad y DTOs.
	 *
	 * @return Instancia de {@link NotaMapper}.
	 */
	@Override
	protected NotaMapper mapper() {
		return mapper;
	}

	/**
	 * Columnas incluidas en las sentencias SELECT.
	 *
	 * @return Cadena con las columnas separadas por coma.
	 */
	@Override
	protected String selectColumns() {
		return SELECT_COLUMNS;
	}

	/**
	 * Construye la entidad {@link Nota} desde un {@link ResultSet}.
	 *
	 * @param rs ResultSet actual.
	 * @return Entidad mapeada {@link Nota}.
	 * @throws SQLException si ocurre un error al leer los datos.
	 */
	@Override
	protected Nota fromResultSet(ResultSet rs) throws SQLException {
		return mapper.fromResultSet(rs);
	}

	/**
	 * Columnas utilizadas en la sentencia INSERT.
	 *
	 * @return Lista de columnas permitidas para inserción.
	 */
	@Override
	protected String insertColumnsSql() {
		return INSERT_COLUMNS;
	}

	/**
	 * Asigna parámetros de un INSERT en el PreparedStatement.
	 *
	 * @param stmt PreparedStatement de la sentencia INSERT.
	 * @param e    Entidad {@link Nota} con los valores a insertar.
	 * @throws SQLException si ocurre un error al asignar valores.
	 */
	@Override
	protected void fillInsert(PreparedStatement stmt, Nota e) throws SQLException {
		mapper.fillInsert(stmt, e);
	}

	/**
	 * Retorna la definición de columnas para la sentencia UPDATE.
	 *
	 * @return Cadena que contiene "columna = ?".
	 */
	@Override
	protected String updateSetSql() {
		return UPDATE_COLUMNS;
	}

	/**
	 * Asigna parámetros para una sentencia UPDATE usando los valores
	 * de la entidad {@link Nota}.
	 *
	 * @param stmt PreparedStatement del UPDATE.
	 * @param e    Entidad actualizada.
	 * @throws SQLException si ocurre un error al asignar valores.
	 */
	@Override
	protected void fillUpdate(PreparedStatement stmt, Nota e) throws SQLException {
		mapper.fillUpdate(stmt, e);
	}

	/**
	 * Construye dinámicamente el fragmento {@code SET} para operaciones PATCH,
	 * junto con los parámetros asociados, según los valores presentes en
	 * {@link NotaPatch}.
	 *
	 * @param sql    Acumulador del fragmento SQL.
	 * @param params Lista de parámetros del PATCH.
	 * @param patch  DTO con campos parcialmente actualizados.
	 */
	@Override
	protected void fillPatch(StringBuilder sql, List<Object> params, NotaPatch patch) {
		mapper.fillPatch(sql, params, patch);
	}

	/**
	 * Obtiene promedios finales por estudiante y asignatura según
	 * filtros opcionales de ciclo y curso.
	 *
	 * <p>
	 * Realiza JOIN entre:
	 * <ul>
	 * <li>{@code estudiante}</li>
	 * <li>{@code nota}</li>
	 * <li>{@code asignatura}</li>
	 * </ul>
	 * </p>
	 *
	 * @param ciclo ciclo académico opcional.
	 * @param curso curso opcional.
	 * @return Lista de {@link PromedioResponse} con los promedios agrupados.
	 * @throws Exception si ocurre un error en la consulta.
	 */
	public List<PromedioResponse> getPromedios(Integer ciclo, Integer curso) throws Exception {

		StringBuilder sql = new StringBuilder(
				"SELECT e.nombres AS estudiante, a.nombre AS asignatura, AVG(n.nota_final) AS promedio " +
						"FROM nota n " +
						"JOIN estudiante e ON n.estudiante_id = e.id " +
						"JOIN asignatura a ON n.asignatura_id = a.id " +
						"WHERE 1=1 ");

		List<Object> params = new ArrayList<>();

		if (ciclo != null) {
			sql.append("AND e.ciclo = ? ");
			params.add(ciclo);
		}

		if (curso != null) {
			sql.append("AND a.curso = ? ");
			params.add(curso);
		}

		sql.append("GROUP BY e.nombres, a.nombre ORDER BY e.nombres, a.nombre");

		List<PromedioResponse> list = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					PromedioResponse pr = new PromedioResponse();
					pr.setEstudiante(rs.getString("estudiante"));
					pr.setAsignatura(rs.getString("asignatura"));
					pr.setPromedio(rs.getDouble("promedio"));
					list.add(pr);
				}
			}
		}

		return list;
	}
}
