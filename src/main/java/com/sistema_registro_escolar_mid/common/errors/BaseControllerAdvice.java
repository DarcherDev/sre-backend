package com.sistema_registro_escolar_mid.common.errors;


import com.sistema_registro_escolar_mid.common.exceptions.BaseExceptions;
import com.sistema_registro_escolar_mid.common.utilities.BaseConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice
public class BaseControllerAdvice {

    protected static final ConcurrentHashMap<String, Integer> HTTP_STATUS_COD_MAP = new ConcurrentHashMap<>();

    public BaseControllerAdvice() {
        HTTP_STATUS_COD_MAP.put(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value());
        HTTP_STATUS_COD_MAP.put(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value());
        HTTP_STATUS_COD_MAP.put(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    protected ResponseEntity<Error> getErrorResponseEntity(BaseExceptions exception) {
        HttpStatus httpStatus = exception.getHttpStatus();
        String simpleNameException = (httpStatus != null) ? httpStatus.name() : null;
        Integer httpStatusCod = (simpleNameException != null) ? HTTP_STATUS_COD_MAP.get(simpleNameException) : null;
        String messageException = exception.getMessage();

        if (httpStatusCod == null) {
            Error error = Error.builder()
                    .httpStatusCod(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatusName(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(BaseConstants.AN_ERROR_OCCURRED_PLEASE_CONTACT_THE_ADMINISTRATOR)
                    .build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Error error = Error.builder()
                .httpStatusCod(httpStatusCod)
                .httpStatusName(HttpStatus.valueOf(httpStatusCod))
                .message(messageException)
                .build();
        return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCod));
    }
}