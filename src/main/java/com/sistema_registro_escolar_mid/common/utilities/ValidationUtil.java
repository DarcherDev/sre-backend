package com.sistema_registro_escolar_mid.common.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String CORREO_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String TELEFONO_PATTERN = "^3\\d{9}$";
    private static final Pattern CORREO_REGEX = Pattern.compile(CORREO_PATTERN);

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Valida si un correo electrónico tiene un formato correcto.
     * @param email Correo a validar.
     * @return Mensaje de error si el correo es inválido, o null si es válido.
     */
    public static boolean validateEmail(String email) {
        return email == null || !CORREO_REGEX.matcher(email).matches(); // Indica que el correo es válido
    }

    /**
     *Valida si una cadena es un número compuesto solo por dígitos y que empieza con el número '3'.*
     * @param input La cadena que se desea validar.
     * @return true si la cadena no es nula, contiene solo dígitos y comienza con '3'; false en caso contrario.
     */
    public static boolean esNumeroValido(String input) {
        // Verifica si es numérico y comienza con 3
        return !isNullOrEmpty(input) && input.matches(TELEFONO_PATTERN);
    }

    /**
     *Valida si una fecha en formato dd/MM/yyyy no es una fecha futura.*
     *@param fechaStr La fecha como cadena (formato dd/MM/yyyy).
     *@return true si la fecha es válida y no es futura, false si es futura o tiene formato inválido.
     */
    public static boolean esFechaValida(String fechaStr) {
        if (fechaStr == null) return false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // No permitir fechas como 32/01/2020

        try {
            Date fecha = sdf.parse(fechaStr);
            Date hoy = new Date();

            return !fecha.after(hoy); // Retorna false si la fecha es futura
        } catch (ParseException e) {
            return false; // Fecha mal formada
        }
    }
}
