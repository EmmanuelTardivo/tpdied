package com.tpdied.forms;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.tpdied.dto.SucursalDTO;

/**
 * Clase de utilidad para validar y convertir datos relacionados con objetos
 * Sucursal.
 */
public class SucursalForm {

    /**
     * Valida y convierte los datos proporcionados en un objeto SucursalDTO.
     *
     * @param nombre       El nombre de la Sucursal.
     * @param horaApertura La hora de apertura de la Sucursal en formato "HH:mm".
     * @param horaCierre   La hora de cierre de la Sucursal en formato "HH:mm".
     * @param estado       El estado de la Sucursal (abierta o cerrada).
     * @return Objeto SucursalDTO con los datos validados.
     * @throws IllegalArgumentException Si alguno de los datos proporcionados es
     *                                  inválido.
     */
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
        if (horaCierre == null || horaCierre.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de cierre no puede ser nula o vacía.");
        }
        try {
            LocalTime.parse(horaCierre);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La hora de cierre debe tener un formato de hora válido (HH:mm).");
        }
        if (!LocalTime.parse(horaCierre).isAfter(LocalTime.parse(horaApertura))) {
            throw new IllegalArgumentException("La hora de cierre debe ser posterior a la hora de apertura.");
        }
    }

    private static void validarHoraApertura(String horaApertura) {
        if (horaApertura == null || horaApertura.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de apertura no puede ser nula o vacía.");
        }
        try {
            LocalTime.parse(horaApertura);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La hora de apertura debe tener un formato de hora válido (HH:mm).");
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
