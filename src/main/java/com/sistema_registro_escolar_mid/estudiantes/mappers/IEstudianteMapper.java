package com.sistema_registro_escolar_mid.estudiantes.mappers;


import com.sistema_registro_escolar_mid.estudiantes.dto.EstudianteDto;
import com.sistema_registro_escolar_mid.estudiantes.model.EstudianteModel;
import org.mapstruct.Mapper;

@Mapper
public interface IEstudianteMapper {

    EstudianteDto toEstudianteDto(EstudianteModel estudianteModel);

    EstudianteModel toEstudianteModel(EstudianteDto estudianteDto);

}
