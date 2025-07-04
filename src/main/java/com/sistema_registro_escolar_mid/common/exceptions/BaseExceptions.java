package com.sistema_registro_escolar_mid.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseExceptions extends RuntimeException {

    private HttpStatus httpStatus;
    private String mensaje;

    public BaseExceptions(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.mensaje = message;
    }

}