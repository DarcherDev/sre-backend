package com.sistema_registro_escolar_mid.personas.mappers;


import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import org.mapstruct.Mapper;

@Mapper
public interface IPersonaMapper {

    PersonaDto toPersonaDto(PersonaModel personaModel);

    PersonaModel toPersonaModel(PersonaDto personaDto);

}
