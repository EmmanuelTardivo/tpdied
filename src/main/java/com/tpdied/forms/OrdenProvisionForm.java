package com.tpdied.forms;

import java.time.LocalDateTime;
import java.util.Map;

import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;

/**
 * Clase de utilidad para validar y convertir datos relacionados con objetos OrdenProvision.
 */
public class OrdenProvisionForm {

    /**
     * Valida y convierte los datos proporcionados en un objeto OrdenProvisionDTO.
     *
     * @param sucursalDestino      La Sucursal de destino de la OrdenProvision.
     * @param limiteHoras          El límite de horas de la OrdenProvision como un valor numérico en formato string.
     * @param itemsProductoCantidad Mapa que contiene los productos y su cantidad para la OrdenProvision.
     * @return Objeto OrdenProvisionDTO con los datos validados.
     * @throws IllegalArgumentException Si alguno de los datos proporcionados es inválido.
     */
    public static OrdenProvisionDTO validarOrdenProvision(SucursalDTO sucursalDestino, String limiteHoras, Map<ProductoDTO, Integer> itemsProductoCantidad) {
        int limiteHorasInt = validarLimiteHoras(limiteHoras);

        OrdenProvisionDTO result = new OrdenProvisionDTO();
        result.setFechaOrden(LocalDateTime.now());
        result.setLimiteHoras(limiteHorasInt);
        result.setSucursalDestino(sucursalDestino);
        result.setItemsProductoCantidad(itemsProductoCantidad);
        return result;
    }

    private static int validarLimiteHoras(String limiteHoras) {
        if (limiteHoras == null || limiteHoras.trim().isEmpty()) {
            throw new IllegalArgumentException("El límite de horas no puede ser nulo o vacío.");
        }
        try {
            int limiteHorasInt = Integer.parseInt(limiteHoras);
            if (limiteHorasInt < 0) {
                throw new IllegalArgumentException("El límite de horas debe ser un número entero positivo mayor a 0.");
            }
            return limiteHorasInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El límite de horas debe ser un número entero válido.");
        }
    }
}
