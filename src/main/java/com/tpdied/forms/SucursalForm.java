package com.tpdied.forms;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.tpdied.dto.SucursalDTO;

public class SucursalForm {// revisar si el id se puede asignar o lo dejamos autoincrement
    public static SucursalDTO validarSucursal(String nombre, String horaApertura, String horaCierre, Boolean estado)
            throws Exception {
        if (!validarNombre(nombre) || !validarHoraApertura(horaApertura)
                || !validarHoraCierre(horaApertura, horaCierre))
            throw new Exception("Datos invalidos. Por favor revisar.");
        SucursalDTO result = new SucursalDTO();
        result.setNombre(nombre);
        result.setHoraApertura(LocalTime.parse(horaApertura));
        result.setHoraCierre(LocalTime.parse(horaCierre));
        result.setEstado(estado);
        return result;
    }

    private static boolean validarHoraCierre(String horaApertura, String horaCierre) {

        return validarHoraApertura(horaCierre) &&
            LocalTime.parse(horaCierre).isAfter(LocalTime.parse(horaApertura));
    }

    private static boolean validarHoraApertura(String horaApertura) {
        if (horaApertura == null || horaApertura.trim().isEmpty()) {
            return false;
        }
        try {
            LocalTime.parse(horaApertura);
            return true; // Si no lanza una excepción, la hora es válida
        } catch (DateTimeParseException e) {
            return false; // Si ocurre una excepción, la hora no es válida
        }
    }

    private static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 255) {
            return false;
        }

        if (!nombre.matches("^[a-zA-Z0-9][a-zA-Z0-9\\s]*[a-zA-Z0-9]$")) {
            return false;
        }

        return true;
    }

}
