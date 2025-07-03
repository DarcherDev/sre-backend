-- Tabla Persona 
CREATE TABLE persona (
    pe_id_persona SERIAL PRIMARY KEY,
    pe_nombre VARCHAR(100) NOT NULL,
    pe_apellido VARCHAR(100) NOT NULL,
    pe_fecha_nacimiento DATE NOT NULL,
    pe_email VARCHAR(100) UNIQUE NOT NULL,
    pe_telefono VARCHAR(20) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('ESTUDIANTE', 'PROFESOR'))
);
alter table persona owner to postgres;

-- Tabla Estudiante 
CREATE TABLE estudiante (
    es_id_persona INTEGER PRIMARY KEY,
    es_numero_matricula VARCHAR(20) UNIQUE NOT NULL,
    es_grado VARCHAR(50) NOT NULL,
    CONSTRAINT fk_estudiante_persona FOREIGN KEY (pe_id_persona) 
        REFERENCES persona (pe_id_persona) ON DELETE CASCADE
);
alter table estudiante owner to postgres;

-- Tabla Profesor 
CREATE TABLE profesor (
    pr_id_persona INTEGER PRIMARY KEY,
    pr_especialidad VARCHAR(100) NOT NULL,
    pr_fecha_contratacion DATE NOT NULL,
    CONSTRAINT fk_profesor_persona FOREIGN KEY (pe_id_persona) 
        REFERENCES persona (pe_id_persona) ON DELETE CASCADE
);
alter table profesor owner to postgres;

-- Insertar datos
INSERT INTO persona (nombre, apellido, fecha_nacimiento, email, telefono, tipo)
VALUES ('Juan', 'Pérez', '1995-05-15', 'juan.perez@email.com', '5551234567', 'ESTUDIANTE');

INSERT INTO estudiante (id_persona, numero_matricula, grado)
VALUES (currval('persona_id_persona_seq'), 'MAT2023001', '10mo');

INSERT INTO persona (nombre, apellido, fecha_nacimiento, email, telefono, tipo)
VALUES ('María', 'Gómez', '1980-08-22', 'maria.gomez@email.com', '5557654321', 'PROFESOR');

INSERT INTO profesor (id_persona, especialidad, fecha_contratacion)
VALUES (currval('persona_id_persona_seq'), 'Matemáticas', '2015-03-10');