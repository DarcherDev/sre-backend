package com.sistema_registro_escolar_mid.estudiantes.exceptions;

import com.sistema_registro_escolar_mid.common.exceptions.BaseExceptions;
import org.springframework.http.HttpStatus;

public class EstudianteException extends BaseExceptions {

    public EstudianteException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}
