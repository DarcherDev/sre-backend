package com.sistema_registro_escolar_mid.common.utilities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Format {

    public static Date parseToDate(String format, String dateValue) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            java.util.Date parsedDate = sdf.parse(dateValue);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format or value: " + dateValue, e);
        }
    }

}
