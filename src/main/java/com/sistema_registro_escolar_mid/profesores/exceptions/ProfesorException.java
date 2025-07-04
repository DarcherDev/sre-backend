package com.sistema_registro_escolar_mid.profesores.exceptions;

import com.sistema_registro_escolar_mid.common.exceptions.BaseExceptions;
import org.springframework.http.HttpStatus;

public class ProfesorException extends BaseExceptions {

    public ProfesorException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}
