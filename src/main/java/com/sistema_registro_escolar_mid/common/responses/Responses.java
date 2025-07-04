package com.sistema_registro_escolar_mid.common.responses;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Responses {

    public static final Function<String, Map<String, Object>> createMapResponse = mensaje -> {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", mensaje);
        return response;
    };

}
