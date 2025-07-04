package com.sistema_registro_escolar_mid.common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseExceptions.class)
    public ResponseEntity<Map<String, Object>> handleBaseException(BaseExceptions ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getHttpStatus().value());
        response.put("error", ex.getHttpStatus());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

}