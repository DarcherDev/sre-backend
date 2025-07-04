package com.sistema_registro_escolar_mid.profesores.mappers;


import com.sistema_registro_escolar_mid.profesores.dto.ProfesorDto;
import com.sistema_registro_escolar_mid.profesores.model.ProfesorModel;
import org.mapstruct.Mapper;

@Mapper
public interface IProfesorMapper {

    ProfesorDto toProfesorDto(ProfesorModel profesorModel);

    ProfesorModel toProfesorModel(ProfesorDto profesorDto);

}
