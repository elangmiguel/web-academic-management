package edu.unipiloto.notas.model.template;

import java.time.LocalDateTime;

/**
 * Clase base para todas las entidades del dominio.
 * Proporciona identificador primario y campos de auditoría
 * estándar utilizados en operaciones de persistencia.
 */
public abstract class BaseModel {

    /**
     * Identificador único de la entidad.
     */
    private Long id;

    /**
     * Marca temporal de creación del registro.
     */
    private LocalDateTime createdAt;

    /**
     * Marca temporal de la última actualización del registro.
     */
    private LocalDateTime updatedAt;

    /**
     * Obtiene el identificador único de la entidad.
     *
     * @return id de la entidad
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único de la entidad.
     *
     * @param id valor del identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora de creación del registro.
     *
     * @return marca temporal de creación
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Asigna la fecha y hora de creación del registro.
     *
     * @param createdAt marca temporal a registrar
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Obtiene la fecha y hora de la última actualización del registro.
     *
     * @return marca temporal de actualización
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Asigna la fecha y hora de la última actualización del registro.
     *
     * @param updatedAt marca temporal nueva
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
