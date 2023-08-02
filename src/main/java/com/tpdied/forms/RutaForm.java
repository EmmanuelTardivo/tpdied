package com.tpdied.forms;

import java.time.Duration;

import com.tpdied.dto.RutaDTO;
import com.tpdied.dto.SucursalDTO;

/**
 * Clase de utilidad para validar y convertir datos relacionados con objetos
 * Ruta.
 */
public class RutaForm {

    /**
     * Valida y convierte los datos proporcionados en un objeto RutaDTO.
     *
     * @param capacidadEnKilos La capacidad en kilos de la Ruta.
     * @param sucursalOrigen   La Sucursal de origen de la Ruta.
     * @param sucursalDestino  La Sucursal de destino de la Ruta.
     * @param duracionViaje    La duración del viaje de la Ruta en formato "HH:mm".
     * @param estado           El estado de la Ruta (activa o inactiva).
     * @return Objeto RutaDTO con los datos validados.
     * @throws IllegalArgumentException Si alguno de los datos proporcionados es
     *                                  inválido.
     */
    public static RutaDTO validarRuta(String capacidadEnKilos, SucursalDTO sucursalOrigen, SucursalDTO sucursalDestino,
            String duracionViaje, Boolean estado) {
        double capacidadDouble = validarCapacidadEnKilos(capacidadEnKilos);
        Duration duracionViajeTime = validarDuracionViaje(duracionViaje);

        RutaDTO result = new RutaDTO();
        result.setCapacidadEnKilos(capacidadDouble);
        result.setSucursalOrigen(sucursalOrigen);
        result.setSucursalDestino(sucursalDestino);
        result.setDuracionViaje(duracionViajeTime);
        result.setEstado(estado);
        return result;
    }

    private static double validarCapacidadEnKilos(String capacidadEnKilos) {
        if (capacidadEnKilos == null || capacidadEnKilos.isEmpty()) {
            throw new IllegalArgumentException("La capacidad en kilos no puede ser nula o vacía.");
        }
        try {
            double capacidadDouble = Double.parseDouble(capacidadEnKilos);
            if (capacidadDouble < 0.0) {
                throw new IllegalArgumentException("La capacidad en kilos debe ser un número positivo mayor a 0.");
            }
            return capacidadDouble;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La capacidad en kilos debe ser un número válido.");
        }
    }

    private static Duration validarDuracionViaje(String duracionViaje) {
        if (duracionViaje == null || duracionViaje.trim().isEmpty()) {
            throw new IllegalArgumentException("La duración del viaje no puede ser nula o vacía.");
        }
        String[] partes = duracionViaje.split(":");
        if (partes.length != 2) {
            throw new IllegalArgumentException("El límite de tiempo debe tener un formato de hora válido (HH:mm).");
        }
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
    
        return Duration.ofHours(horas).plusMinutes(minutos);
    }
}
