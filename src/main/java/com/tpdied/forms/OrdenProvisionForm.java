package com.tpdied.forms;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;

public class OrdenProvisionForm {
    public static OrdenProvisionDTO validarOrdenProvision(String fechaOrden, SucursalDTO sucursalDestino, String limiteHoras, Map<ProductoDTO, Integer> itemsProductoCantidad) throws Exception {
        if (!validarFechaOrden(fechaOrden) || !validarLimiteHoras(limiteHoras))
            throw new Exception("Fecha y/o limite de horas invalidos. Por favor revisar.");
        OrdenProvisionDTO result = new OrdenProvisionDTO();
        result.setFechaOrden(LocalDateTime.parse(fechaOrden));
        result.setLimiteHoras(Integer.parseInt(limiteHoras));
        result.setSucursalDestino(sucursalDestino);
        result.setItemsProductoCantidad(itemsProductoCantidad);
        return result;
    }

    private static boolean validarFechaOrden(String fechaOrden){
        if (fechaOrden == null || fechaOrden.trim().isEmpty()) {
            return false;
        }
        try {
            LocalTime.parse(fechaOrden);
            return true; // Si no lanza una excepción, la hora es válida
        } catch (DateTimeParseException e) {
            return false; // Si ocurre una excepción, la hora no es válida
        }
    }

    private static boolean validarLimiteHoras(String limiteHoras){
        if (limiteHoras == null || limiteHoras.trim().isEmpty()) {
            return false;
        }
        try {
            return Integer.parseInt(limiteHoras) > 0; // Si no lanza una excepción y es mayor a 0, es válido
        } catch (DateTimeParseException e) {
            return false; // Si ocurre una excepción, no es válido
        }
    }
}
