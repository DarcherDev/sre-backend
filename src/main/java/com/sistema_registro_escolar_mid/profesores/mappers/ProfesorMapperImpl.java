package com.sistema_registro_escolar_mid.profesores.mappers;

import com.sistema_registro_escolar_mid.common.utilities.Format;
import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.mappers.IPersonaMapper;
import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import com.sistema_registro_escolar_mid.profesores.dto.ProfesorDto;
import com.sistema_registro_escolar_mid.profesores.model.ProfesorModel;
import org.mapstruct.Named;


public class ProfesorMapperImpl implements IProfesorMapper {

    private final IPersonaMapper personaMapper;

    public ProfesorMapperImpl(IPersonaMapper personaMapper) {
        this.personaMapper = personaMapper;
    }

    @Override
    public ProfesorDto toProfesorDto(ProfesorModel profesorModel){
        
        return ProfesorDto.builder()
                .id(profesorModel.getId())
                .especialidad(profesorModel.getEspecialidad())
                .contratacionFecha(profesorModel.getContratacionFecha().toString())
                .persona(this.mapPersonaDto(profesorModel.getPersona()))
                .build();
    }

    @Override
    public ProfesorModel toProfesorModel(ProfesorDto profesorDto) {

        return ProfesorModel.builder()
                .id(profesorDto.getId())
                .especialidad(profesorDto.getEspecialidad())
                .contratacionFecha(Format.parseToDate("yyyy-MM-dd", profesorDto.getContratacionFecha()))
                .persona(this.mapPersonaModel(profesorDto.getPersona()))
                .build();
    }
    
    @Named("mapPersonaModel")
    public PersonaModel mapPersonaModel(PersonaDto PersonaDto) {
        return this.personaMapper.toPersonaModel(PersonaDto);
    }

    @Named("mapPersonaDto")
    public PersonaDto mapPersonaDto(PersonaModel personaModel) {
        return this.personaMapper.toPersonaDto(personaModel);
    }
}

