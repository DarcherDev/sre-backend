package com.sistema_registro_escolar_mid.estudiantes.config;

import com.sistema_registro_escolar_mid.estudiantes.mappers.IEstudianteMapper;
import com.sistema_registro_escolar_mid.estudiantes.mappers.EstudianteMapperImpl;
import com.sistema_registro_escolar_mid.personas.mappers.PersonaMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstudianteConfig {

    @Bean
    public IEstudianteMapper iEstudianteMapper(){
        return new EstudianteMapperImpl(new PersonaMapperImpl());
    }

}
