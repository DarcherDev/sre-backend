package com.sistema_registro_escolar_mid.personas.errors;


import com.sistema_registro_escolar_mid.common.errors.BaseControllerAdvice;
import com.sistema_registro_escolar_mid.common.errors.Error;
import com.sistema_registro_escolar_mid.personas.exceptions.PersonaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PersonaControllerAdvice extends BaseControllerAdvice {

    @ExceptionHandler(PersonaException.class)
    public final ResponseEntity<Error> retrievePersonaExceptionHandler(PersonaException personaException) {
        return getErrorResponseEntity(personaException);
    }
}
