package com.sistema_registro_escolar_mid.profesores.config;

import com.sistema_registro_escolar_mid.personas.mappers.PersonaMapperImpl;
import com.sistema_registro_escolar_mid.profesores.mappers.ProfesorMapperImpl;
import com.sistema_registro_escolar_mid.profesores.mappers.IProfesorMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfesorConfig {

    @Bean
    public IProfesorMapper iProfesorMapper(){
        return new ProfesorMapperImpl(new PersonaMapperImpl());
    }

}
