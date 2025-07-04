package com.sistema_registro_escolar_mid.personas.mappers;


import com.sistema_registro_escolar_mid.common.utilities.Format;
import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

public class PersonaMapperImpl implements IPersonaMapper {

    @Override
    public PersonaDto toPersonaDto(PersonaModel personaModel){

        return PersonaDto.builder()
                .id(personaModel.getId())
                .firstName(personaModel.getNames())
                .lastNames(personaModel.getLastNames())
                .birthDate(personaModel.getBirthDate().toString())
                .email(personaModel.getEmail())
                .phone(personaModel.getPhone())
                .build();
    }

    @Override
    public PersonaModel toPersonaModel(PersonaDto personaDto) {

        return PersonaModel.builder()
                .id(personaDto.getId())
                .email(personaDto.getEmail())
                .names(personaDto.getFirstName())
                .lastNames(personaDto.getLastNames())
                .birthDate(Format.parseToDate("yyyy-MM-dd", personaDto.getBirthDate()))
                .phone(personaDto.getPhone())
                .build();
    }

}
