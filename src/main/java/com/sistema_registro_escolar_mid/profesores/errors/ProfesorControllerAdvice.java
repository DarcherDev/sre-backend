package com.sistema_registro_escolar_mid.profesores.errors;


import com.sistema_registro_escolar_mid.common.errors.BaseControllerAdvice;
import com.sistema_registro_escolar_mid.common.errors.Error;
import com.sistema_registro_escolar_mid.profesores.exceptions.ProfesorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProfesorControllerAdvice extends BaseControllerAdvice {

    @ExceptionHandler(ProfesorException.class)
    public final ResponseEntity<Error> retrievePersonaExceptionHandler(ProfesorException profesorException) {
        return getErrorResponseEntity(profesorException);
    }
}
