package edu.unipiloto.notas.mapper.factory;

import edu.unipiloto.notas.mapper.AsignaturaMapper;
import edu.unipiloto.notas.mapper.DocenteMapper;
import edu.unipiloto.notas.mapper.EstudianteMapper;
import edu.unipiloto.notas.mapper.NotaMapper;

/**
 * Fábrica centralizada para la obtención de instancias únicas (singleton)
 * de los mappers de la aplicación.
 *
 * <p>Esta clase proporciona acceso controlado a los mappers encargados de
 * transformar entidades en sus respectivos DTOs y viceversa. Todas las
 * instancias son creadas de forma estática y reutilizadas a lo largo del
 * ciclo de vida de la aplicación.</p>
 *
 * <p>La clase está declarada como {@code final} para evitar herencia y su
 * constructor es privado, ya que no debe instanciarse.</p>
 */
public final class MapperFactory {

    /** Instancia única del mapper de asignaturas. */
    private static final AsignaturaMapper ASIGNATURA_MAPPER = new AsignaturaMapper();

    /** Instancia única del mapper de docentes. */
    private static final DocenteMapper DOCENTE_MAPPER = new DocenteMapper();

    /** Instancia única del mapper de estudiantes. */
    private static final EstudianteMapper ESTUDIANTE_MAPPER = new EstudianteMapper();

    /** Instancia única del mapper de notas. */
    private static final NotaMapper NOTA_MAPPER = new NotaMapper();

    /**
     * Constructor privado para impedir la creación de instancias externas.
     */
    private MapperFactory() {}

    /**
     * Retorna la instancia única del {@link AsignaturaMapper}.
     *
     * @return instancia singleton de AsignaturaMapper
     */
    public static AsignaturaMapper asignatura() {
        return ASIGNATURA_MAPPER;
    }

    /**
     * Retorna la instancia única del {@link DocenteMapper}.
     *
     * @return instancia singleton de DocenteMapper
     */
    public static DocenteMapper docente() {
        return DOCENTE_MAPPER;
    }

    /**
     * Retorna la instancia única del {@link EstudianteMapper}.
     *
     * @return instancia singleton de EstudianteMapper
     */
    public static EstudianteMapper estudiante() {
        return ESTUDIANTE_MAPPER;
    }

    /**
     * Retorna la instancia única del {@link NotaMapper}.
     *
     * @return instancia singleton de NotaMapper
     */
    public static NotaMapper nota() {
        return NOTA_MAPPER;
    }
}
