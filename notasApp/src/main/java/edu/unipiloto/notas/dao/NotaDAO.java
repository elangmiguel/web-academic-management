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
 * DAO para la entidad {@link Nota}.
 */
public class NotaDAO extends CrudDAO<Nota, NotaRequest, NotaResponse, NotaPatch> {

	private static final String TABLE_NAME = "nota";
	private static final String SELECT_COLUMNS = "id, estudiante_id, asignatura_id, nota1, nota2, nota_final, observaciones, created_at, updated_at";
	private static final String INSERT_COLUMNS = "estudiante_id, asignatura_id, nota1, nota2, nota_final, observaciones";
	private static final String UPDATE_COLUMNS = "estudiante_id = ?, asignatura_id = ?, nota1 = ?, nota2 = ?, nota_final = ?, observaciones = ?";

	private final NotaMapper mapper = MapperFactory.nota();

	@Override
	protected String tableName() {
		return TABLE_NAME;
	}

	@Override
	protected NotaMapper mapper() {
		return mapper;
	}

	@Override
	protected String selectColumns() {
		return SELECT_COLUMNS;
	}

	@Override
	protected Nota fromResultSet(ResultSet rs) throws SQLException {
		return mapper.fromResultSet(rs);
	}

	@Override
	protected String insertColumnsSql() {
		return INSERT_COLUMNS;
	}

	@Override
	protected void fillInsert(PreparedStatement stmt, Nota e) throws SQLException {
		mapper.fillInsert(stmt, e);
	}

	@Override
	protected String updateSetSql() {
		return UPDATE_COLUMNS;
	}

	@Override
	protected void fillUpdate(PreparedStatement stmt, Nota e) throws SQLException {
		mapper.fillUpdate(stmt, e);
	}

	@Override
	protected void fillPatch(StringBuilder sql, List<Object> params, NotaPatch patch) {
		mapper.fillPatch(sql, params, patch);
	}

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