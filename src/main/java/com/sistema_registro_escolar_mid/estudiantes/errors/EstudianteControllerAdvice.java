package com.sistema_registro_escolar_mid.estudiantes.errors;


import com.sistema_registro_escolar_mid.common.errors.BaseControllerAdvice;
import com.sistema_registro_escolar_mid.common.errors.Error;
import com.sistema_registro_escolar_mid.estudiantes.exceptions.EstudianteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EstudianteControllerAdvice extends BaseControllerAdvice {

    @ExceptionHandler(EstudianteException.class)
    public final ResponseEntity<Error> retrievePersonaExceptionHandler(EstudianteException estudianteException) {
        return getErrorResponseEntity(estudianteException);
    }
}
