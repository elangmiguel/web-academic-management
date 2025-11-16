package edu.unipiloto.notas.dto.response.template;

import java.time.LocalDateTime;

/**
 * Clase base para todos los DTO de respuesta del sistema.
 * Proporciona atributos comunes como el identificador del recurso,
 * así como las marcas de tiempo de creación y última actualización.
 *
 * <p>Las entidades de respuesta específicas deben extender esta clase
 * para asegurar consistencia estructural en las respuestas API.</p>
 */
public abstract class BaseResponseDTO {

    /** Identificador único del recurso. */
    private Long id;

    /** Fecha y hora en que el recurso fue creado. */
    private LocalDateTime createdAt;

    /** Fecha y hora de la última actualización del recurso. */
    private LocalDateTime updatedAt;

    /**
     * Obtiene el identificador del recurso.
     *
     * @return id del recurso
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del recurso.
     *
     * @param id valor del identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora de creación del recurso.
     *
     * @return marca de tiempo de creación
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Establece la fecha y hora de creación del recurso.
     *
     * @param createdAt fecha y hora de creación
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Obtiene la fecha y hora de la última actualización del recurso.
     *
     * @return marca de tiempo de actualización
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Establece la fecha y hora de la última actualización del recurso.
     *
     * @param updatedAt fecha y hora de actualización
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
