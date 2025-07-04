-- Tabla Persona 
CREATE TABLE persona (
    pe_id_persona SERIAL PRIMARY KEY,
    pe_nombre VARCHAR(255) NOT NULL,
    pe_apellido VARCHAR(255) NOT NULL,
    pe_fecha_nacimiento DATE NOT NULL,
    pe_email VARCHAR(255) UNIQUE NOT NULL,
    pe_telefono VARCHAR(15) NOT NULL
);
alter table persona owner to postgres;

-- Tabla Estudiante 
CREATE TABLE estudiante (
    es_id_estudiante SERIAL PRIMARY KEY,
    es_numero_matricula VARCHAR(20) UNIQUE NOT NULL,
    es_grado VARCHAR(50) NOT NULL,
	es_id_persona INT, 
    CONSTRAINT fk_estudiante_persona FOREIGN KEY (es_id_persona)
        REFERENCES persona (pe_id_persona)
);
alter table estudiante owner to postgres;

-- Tabla Profesor 
CREATE TABLE profesor (
    pr_id_profesor SERIAL PRIMARY KEY,
    pr_especialidad VARCHAR(100) NOT NULL,
    pr_fecha_contratacion DATE NOT NULL,
	pr_id_persona INT, 
    CONSTRAINT fk_profesor_persona FOREIGN KEY (pr_id_persona) 
        REFERENCES persona (pe_id_persona) 
);
alter table profesor owner to postgres;
