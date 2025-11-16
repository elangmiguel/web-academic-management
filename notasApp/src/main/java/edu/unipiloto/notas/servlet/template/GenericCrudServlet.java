package edu.unipiloto.notas.servlet.template;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import edu.unipiloto.notas.dao.template.CrudDAO;
import edu.unipiloto.notas.model.template.BaseModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servlet genérico para exponer operaciones CRUD completas
 * (GET, POST, PUT, PATCH, DELETE) sobre cualquier entidad del sistema.
 *
 * Permite manejar DTOs de solicitud, respuesta y parche, delegando
 * la lógica de persistencia en un {@link CrudDAO} especializado.
 *
 * @param <Entity> Tipo de entidad base que extiende {@link BaseModel}
 * @param <Req>    Tipo del DTO de creación/actualización completa
 * @param <Res>    Tipo del DTO de respuesta
 * @param <Patch>  Tipo del DTO de actualización parcial (PATCH)
 * @param <DAO>    Implementación concreta de {@link CrudDAO}
 */
public abstract class GenericCrudServlet<Entity extends BaseModel, Req, Res, Patch, DAO extends CrudDAO<Entity, Req, Res, Patch>>
		extends HttpServlet {

	/** DAO responsable de la persistencia */
	protected final DAO dao;

	/** Serializador JSON */
	protected final Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class,
					(JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> context
							.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
			.registerTypeAdapter(LocalDateTime.class,
					(JsonDeserializer<LocalDateTime>) (json, type, context) -> LocalDateTime.parse(json.getAsString(),
							DateTimeFormatter.ISO_LOCAL_DATE_TIME))
			.create();

	/**
	 * Constructor del servlet CRUD genérico.
	 *
	 * @param dao DAO concreto para manejar la entidad
	 */
	protected GenericCrudServlet(DAO dao) {
		this.dao = dao;
	}

	/**
	 * Serializa un objeto a JSON y lo envía al cliente.
	 *
	 * @param resp respuesta HTTP
	 * @param data objeto a serializar
	 * @throws IOException cuando ocurre un error de escritura
	 */
	protected void writeJson(HttpServletResponse resp, Object data) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		resp.getWriter().write(gson.toJson(data));
	}

	/**
	 * Deserializa el cuerpo JSON de la petición en la clase indicada.
	 *
	 * @param req   petición HTTP
	 * @param clazz clase destino para la deserialización
	 * @param <X>   tipo resultante
	 * @return objeto deserializado
	 * @throws IOException si ocurre un error al leer el cuerpo
	 */
	protected <X> X readJson(HttpServletRequest req, Class<X> clazz) throws IOException {
		return gson.fromJson(req.getReader(), clazz);
	}

	/**
	 * Extrae el identificador numérico desde la ruta del recurso.
	 *
	 * @param req petición HTTP
	 * @return id extraído
	 */
	protected long pathId(HttpServletRequest req) {
		return Long.parseLong(req.getPathInfo().substring(1));
	}

	/**
	 * Maneja errores controladamente devolviendo HTTP 500 y mensaje JSON.
	 *
	 * @param resp respuesta HTTP
	 * @param ex   excepción ocurrida
	 * @throws IOException si falla al escribir la respuesta
	 */
	protected void handleError(HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		writeJson(resp, ex.getMessage());
	}

	/**
	 * GET: lista entidades o devuelve una por ID.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			if (req.getPathInfo() == null || "/".equals(req.getPathInfo())) {
				writeJson(resp, dao.findAll());
			} else {
				writeJson(resp, dao.findById(pathId(req)));
			}
		} catch (Exception ex) {
			handleError(resp, ex);
		}
	}

	/**
	 * POST: inserta un nuevo registro y devuelve el DTO resultante.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			Req body = readJson(req, getRequestClass());
			Res created = dao.insertAndReturn(body);
			writeJson(resp, created);
		} catch (Exception ex) {
			handleError(resp, ex);
		}
	}

	/**
	 * PUT: actualización completa del recurso.
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			Req body = readJson(req, getRequestClass());
			Res updated = dao.updateAndReturn(pathId(req), body);
			writeJson(resp, updated);
		} catch (Exception ex) {
			handleError(resp, ex);
		}
	}

	/**
	 * Intercepta el método PATCH para permitir su manejo explícito.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if ("PATCH".equalsIgnoreCase(req.getMethod())) {
			doPatch(req, resp);
		} else {
			try {
				super.service(req, resp);
			} catch (ServletException e) {
				throw new IOException(e);
			}
		}
	}

	/**
	 * PATCH: actualización parcial del recurso.
	 */
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			Patch body = readJson(req, getPatchClass());
			Res updated = dao.patchAndReturn(pathId(req), body);
			writeJson(resp, updated);
		} catch (Exception ex) {
			handleError(resp, ex);
		}
	}

	/**
	 * DELETE: elimina el recurso por su ID.
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			dao.delete(pathId(req));
			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (Exception ex) {
			handleError(resp, ex);
		}
	}

	/**
	 * @return clase concreta del DTO de solicitud (POST/PUT)
	 */
	protected abstract Class<Req> getRequestClass();

	/**
	 * @return clase concreta del DTO de parches (PATCH)
	 */
	protected abstract Class<Patch> getPatchClass();
}
