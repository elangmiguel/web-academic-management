package edu.unipiloto.notas.mapper.factory;

import edu.unipiloto.notas.mapper.AsignaturaMapper;
import edu.unipiloto.notas.mapper.DocenteMapper;
import edu.unipiloto.notas.mapper.EstudianteMapper;
import edu.unipiloto.notas.mapper.NotaMapper;

public final class MapperFactory {

    // Instancias singleton
    private static final AsignaturaMapper ASIGNATURA_MAPPER = new AsignaturaMapper();
    private static final DocenteMapper DOCENTE_MAPPER = new DocenteMapper();
    private static final EstudianteMapper ESTUDIANTE_MAPPER = new EstudianteMapper();
    private static final NotaMapper NOTA_MAPPER = new NotaMapper();

    private MapperFactory() {}

    public static AsignaturaMapper asignatura() {
        return ASIGNATURA_MAPPER;
    }

    public static DocenteMapper docente() {
        return DOCENTE_MAPPER;
    }

    public static EstudianteMapper estudiante() {
        return ESTUDIANTE_MAPPER;
    }

    public static NotaMapper nota() {
        return NOTA_MAPPER;
    }
}
