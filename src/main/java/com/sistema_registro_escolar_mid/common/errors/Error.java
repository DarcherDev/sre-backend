package com.sistema_registro_escolar_mid.common.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class Error {

    private Integer httpStatusCod;
    private HttpStatus httpStatusName;
    private String message;

}
