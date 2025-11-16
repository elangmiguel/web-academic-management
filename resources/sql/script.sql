-- Base de datos
DROP DATABASE IF EXISTS notasdb;
CREATE DATABASE IF NOT EXISTS notasdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE notasdb;

-- Tabla Estudiante
CREATE TABLE IF NOT EXISTS estudiante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    documento VARCHAR(50) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    ciclo INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla Docente
CREATE TABLE IF NOT EXISTS docente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla Asignatura
CREATE TABLE IF NOT EXISTS asignatura (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    docente_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    intensidad_horaria INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (docente_id) REFERENCES docente(id) ON DELETE CASCADE
);

-- Tabla Nota
CREATE TABLE IF NOT EXISTS nota (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    asignatura_id BIGINT NOT NULL,
    nota1 DECIMAL(5,2),
    nota2 DECIMAL(5,2),
    nota_final DECIMAL(5,2),
    observaciones VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id) ON DELETE CASCADE,
    FOREIGN KEY (asignatura_id) REFERENCES asignatura(id) ON DELETE CASCADE
);

-- Docentes de ejemplo
INSERT INTO docente (nombres, apellidos, correo) VALUES
('Pedro', 'García', 'pedro.garcia@unipiloto.edu.co'),
('Luisa', 'Martínez', 'luisa.martinez@unipiloto.edu.co'),
('Ricardo', 'Hernández', 'ricardo.hernandez@unipiloto.edu.co'),
('Camila', 'Rojas', 'camila.rojas@unipiloto.edu.co'),
('Felipe', 'Cortés', 'felipe.cortes@unipiloto.edu.co'),
('Valentina', 'Moreno', 'valentina.moreno@unipiloto.edu.co'),
('Jorge', 'Reyes', 'jorge.reyes@unipiloto.edu.co'),
('Diana', 'Santos', 'diana.santos@unipiloto.edu.co'),
('Sebastián', 'Medina', 'sebastian.medina@unipiloto.edu.co'),
('Ana', 'Suárez', 'ana.suarez@unipiloto.edu.co'),
('Manuel', 'Álvarez', 'manuel.alvarez@unipiloto.edu.co'),
('Paula', 'Vargas', 'paula.vargas@unipiloto.edu.co'),
('Esteban', 'Castaño', 'esteban.castano@unipiloto.edu.co'),
('Juliana', 'Peña', 'juliana.pena@unipiloto.edu.co'),
('David', 'Calderón', 'david.calderon@unipiloto.edu.co'),
('Natalia', 'Fajardo', 'natalia.fajardo@unipiloto.edu.co'),
('Tomás', 'Serrano', 'tomas.serrano@unipiloto.edu.co'),
('Gabriela', 'Quintero', 'gabriela.quintero@unipiloto.edu.co'),
('Héctor', 'Bermúdez', 'hector.bermudez@unipiloto.edu.co'),
('Sara', 'Velásquez', 'sara.velasquez@unipiloto.edu.co');

-- Estudiantes de ejemplo
INSERT INTO estudiante (nombres, apellidos, documento, correo, ciclo) VALUES
('Daniel', 'Ríos', '20001', 'daniel.rios@unipiloto.edu.co', 1),
('Camilo', 'Jiménez', '20002', 'camilo.jimenez@unipiloto.edu.co', 2),
('Luisa', 'Fernández', '20003', 'luisa.fernandez@unipiloto.edu.co', 3),
('Sofía', 'Martínez', '20004', 'sofia.martinez@unipiloto.edu.co', 1),
('Mateo', 'González', '20005', 'mateo.gonzalez@unipiloto.edu.co', 2),
('Valeria', 'Ramírez', '20006', 'valeria.ramirez@unipiloto.edu.co', 4),
('Samuel', 'Vargas', '20007', 'samuel.vargas@unipiloto.edu.co', 1),
('Isabela', 'Hurtado', '20008', 'isabela.hurtado@unipiloto.edu.co', 3),
('Juan', 'Morales', '20009', 'juan.morales@unipiloto.edu.co', 2),
('Mariana', 'Cárdenas', '20010', 'mariana.cardenas@unipiloto.edu.co', 5),
('Tomás', 'Gutiérrez', '20011', 'tomas.gutierrez@unipiloto.edu.co', 1),
('Simón', 'Núñez', '20012', 'simon.nunez@unipiloto.edu.co', 2),
('Alejandra', 'Pardo', '20013', 'alejandra.pardo@unipiloto.edu.co', 3),
('Andrés', 'Sierra', '20014', 'andres.sierra@unipiloto.edu.co', 1),
('Nicole', 'Cano', '20015', 'nicole.cano@unipiloto.edu.co', 4),
('Santiago', 'Arango', '20016', 'santiago.arango@unipiloto.edu.co', 5),
('Valentina', 'Betancur', '20017', 'valentina.betancur@unipiloto.edu.co', 3),
('Cristian', 'Téllez', '20018', 'cristian.tellez@unipiloto.edu.co', 2),
('Laura', 'Gómez', '20019', 'laura2.gomez@unipiloto.edu.co', 4),
('Emilia', 'Castro', '20020', 'emilia.castro@unipiloto.edu.co', 1);

-- Asignaturas de ejemplo
INSERT INTO asignatura (docente_id, nombre, intensidad_horaria) VALUES
(1, 'Arquitectura de Computadores', 40),
(2, 'Emprendimiento', 25),
(3, 'Programación I', 45),
(4, 'Programación II', 50),
(5, 'Diseño Web', 40),
(6, 'Base de Datos', 48),
(7, 'Ética Profesional', 20),
(8, 'Cálculo Diferencial', 60),
(9, 'Cálculo Integral', 60),
(10, 'Álgebra Lineal', 50),
(11, 'Estadística', 35),
(12, 'Mercadeo', 30),
(13, 'Economía', 25),
(14, 'Redes I', 45),
(15, 'Redes II', 45),
(16, 'Ingeniería de Software', 50),
(17, 'Seguridad Informática', 35),
(18, 'Inteligencia Artificial', 40),
(19, 'Robótica', 30),
(20, 'Sistemas Operativos', 45);


-- Notas de ejemplo
INSERT INTO nota (estudiante_id, asignatura_id, nota1, nota2, nota_final, observaciones) VALUES
(1, 3, 4.2, 4.5, 4.35, 'Buen rendimiento'),
(2, 5, 3.8, 4.0, 3.90, 'Aceptable'),
(3, 8, 2.5, 3.2, 2.85, 'Debe mejorar'),
(4, 10, 4.7, 4.6, 4.65, 'Excelente'),
(5, 2, 3.0, 3.5, 3.25, 'Regular'),
(6, 4, 4.9, 5.0, 4.95, 'Destacado'),
(7, 12, 3.2, 3.1, 3.15, 'Puede mejorar'),
(8, 14, 4.1, 4.0, 4.05, 'Buen desempeño'),
(9, 1, 2.9, 3.3, 3.10, 'Debe reforzar'),
(10, 6, 4.4, 4.3, 4.35, 'Muy bien'),
(11, 7, 2.1, 2.5, 2.30, 'Insuficiente'),
(12, 9, 3.5, 3.8, 3.65, 'Aceptable'),
(13, 11, 4.0, 4.1, 4.05, 'Bien'),
(14, 15, 4.8, 4.7, 4.75, 'Excelente'),
(15, 13, 3.3, 3.6, 3.45, 'Aceptable'),
(16, 18, 2.8, 3.2, 3.00, 'Debe mejorar'),
(17, 19, 4.2, 4.4, 4.30, 'Buen rendimiento'),
(18, 20, 3.9, 4.0, 3.95, 'Bien'),
(19, 1, 4.6, 4.5, 4.55, 'Excelente'),
(20, 2, 3.0, 3.2, 3.10, 'Regular');
