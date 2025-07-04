package com.sistema_registro_escolar_mid.personas.config;

import com.sistema_registro_escolar_mid.personas.mappers.IPersonaMapper;
import com.sistema_registro_escolar_mid.personas.mappers.PersonaMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonaConfig {

    @Bean
    public IPersonaMapper iPersonaMapper(){
        return new PersonaMapperImpl();
    }

}
