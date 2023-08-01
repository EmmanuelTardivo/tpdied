package com.tpdied.forms;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.tpdied.dto.SucursalDTO;

public class SucursalForm {

    public static SucursalDTO validarSucursal(String nombre, String horaApertura, String horaCierre, Boolean estado) {
        validarNombre(nombre);
        validarHoraApertura(horaApertura);
        validarHoraCierre(horaApertura, horaCierre);

        SucursalDTO result = new SucursalDTO();
        result.setNombre(nombre);
        result.setHoraApertura(LocalTime.parse(horaApertura));
        result.setHoraCierre(LocalTime.parse(horaCierre));
        result.setEstado(estado);
        return result;
    }

    private static void validarHoraCierre(String horaApertura, String horaCierre) {
        if (!LocalTime.parse(horaCierre).isAfter(LocalTime.parse(horaApertura))) {
            throw new IllegalArgumentException("La hora de cierre debe ser posterior a la hora de apertura.");
        }
    }

    private static void validarHoraApertura(String horaApertura) {
        try {
            LocalTime.parse(horaApertura);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La hora de apertura es invalida.");
        }
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 255) {
            throw new IllegalArgumentException("El nombre es invalido.");
        }

        if (!nombre.matches("^[a-zA-Z0-9][a-zA-Z0-9\\s]*[a-zA-Z0-9]$|^[a-zA-Z0-9]$")) {
            throw new IllegalArgumentException("El nombre contiene caracteres no permitidos.");
        }
    }
}
