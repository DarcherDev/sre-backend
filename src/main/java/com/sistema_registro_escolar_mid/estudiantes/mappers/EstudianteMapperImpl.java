package com.sistema_registro_escolar_mid.estudiantes.mappers;

import com.sistema_registro_escolar_mid.estudiantes.dto.EstudianteDto;
import com.sistema_registro_escolar_mid.estudiantes.model.EstudianteModel;
import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.mappers.IPersonaMapper;
import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import org.mapstruct.Named;


public class EstudianteMapperImpl implements IEstudianteMapper {

    private final IPersonaMapper personaMapper;

    public EstudianteMapperImpl(IPersonaMapper personaMapper) {
        this.personaMapper = personaMapper;
    }

    @Override
    public EstudianteDto toEstudianteDto(EstudianteModel estudianteModel){
        
        return EstudianteDto.builder()
                .id(estudianteModel.getId())
                .numeroMatricula(estudianteModel.getNumeroMatricula())
                .grado(estudianteModel.getGrado())
                .persona(this.mapPersonaDto(estudianteModel.getPersona()))
                .build();
    }

    @Override
    public EstudianteModel toEstudianteModel(EstudianteDto estudianteDto) {

        return EstudianteModel.builder()
                .id(estudianteDto.getId())
                .numeroMatricula(estudianteDto.getNumeroMatricula())
                .grado(estudianteDto.getGrado())
                .persona(this.mapPersonaModel(estudianteDto.getPersona()))
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
