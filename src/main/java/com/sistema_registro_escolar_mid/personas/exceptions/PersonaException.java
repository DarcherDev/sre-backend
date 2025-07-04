package com.sistema_registro_escolar_mid.personas.exceptions;

import com.sistema_registro_escolar_mid.common.exceptions.BaseExceptions;
import org.springframework.http.HttpStatus;

public class PersonaException extends BaseExceptions {

    public PersonaException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}
