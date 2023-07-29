package com.tpdied.forms;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.tpdied.dto.RutaDTO;
import com.tpdied.dto.SucursalDTO;

public class RutaForm {

    /**
     * Valida los datos de la ruta dada en los args String
     * 
     * @param capacidadEnKilos
     * @param sucursalOrigen
     * @param sucursalDestino
     * @param duracionViaje
     * @param estado
     * @return RutaDTO con los datos transformados de los String.
     * @throws Exception Si no valida algun campo de los dados.
     */
    public static RutaDTO validarRuta(String capacidadEnKilos, SucursalDTO sucursalOrigen, SucursalDTO sucursalDestino,
            String duracionViaje, Boolean estado)
            throws Exception {

        if (!validarCapacidadEnKilos(capacidadEnKilos)
                || !validarDuracionViaje(duracionViaje))
            throw new Exception("Ruta invalida. Por favor revisar.");

        RutaDTO result = new RutaDTO();
        result.setCapacidadEnKilos(Double.parseDouble(capacidadEnKilos));
        result.setSucursalOrigen(sucursalOrigen);
        result.setSucursalDestino(sucursalDestino);
        result.setDuracionViaje(LocalTime.parse(duracionViaje));
        result.setEstado(estado);
        return result;
    }

    /***
     * Valida el formato de la capacidad en kilos de una ruta
     * 
     * @param capacidadEnKilos
     * @return True si tiene formato Double y es positivo. False caso contrario
     */
    private static boolean validarCapacidadEnKilos(String capacidadEnKilos) {
        if (capacidadEnKilos == null || capacidadEnKilos.length() == 0) {
            return false;
        }

        try {
           return Double.parseDouble(capacidadEnKilos) > 0.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /***
     * Valida el formato de la duracion del viaje una ruta
     * 
     * @param duracionViaje
     * @return True si tiene formato de LocalTime. False caso contrario
     */
    private static boolean validarDuracionViaje(String duracionViaje) {
        if (duracionViaje == null || duracionViaje.trim().isEmpty()) {
            return false;
        }

        try {
            LocalTime.parse(duracionViaje);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}