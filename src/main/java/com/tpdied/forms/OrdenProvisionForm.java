package com.tpdied.forms;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;

/**
 * Clase de utilidad para validar y convertir datos relacionados con objetos
 * OrdenProvision.
 */
public class OrdenProvisionForm {

    /**
     * Valida y convierte los datos proporcionados en un objeto OrdenProvisionDTO.
     *
     * @param sucursalDestino       La Sucursal de destino de la OrdenProvision.
     * @param limiteHoras           El límite de horas de la OrdenProvision como un
     *                              valor numérico en formato string.
     * @param itemsProductoCantidad Mapa que contiene los productos y su cantidad
     *                              para la OrdenProvision.
     * @return Objeto OrdenProvisionDTO con los datos validados.
     * @throws IllegalArgumentException Si alguno de los datos proporcionados es
     *                                  inválido.
     */
    public static OrdenProvisionDTO validarOrdenProvision(SucursalDTO sucursalDestino, String limiteTiempo,
            Map<ProductoDTO, Integer> itemsProductoCantidad) throws IllegalArgumentException{
        Duration limiteTiempoTime = validarLimiteTiempo(limiteTiempo);
        validarPedido(itemsProductoCantidad.values());

        OrdenProvisionDTO result = new OrdenProvisionDTO();
        result.setFechaOrden(LocalDateTime.now());
        result.setLimiteTiempo(limiteTiempoTime);
        result.setSucursalDestino(sucursalDestino);
        result.setItemsProductoCantidad(itemsProductoCantidad);
        return result;
    }

    private static Duration validarLimiteTiempo(String limiteTiempo) {
        if (limiteTiempo == null || limiteTiempo.trim().isEmpty()) {
            throw new IllegalArgumentException("El límite de tiempo no puede ser nulo o vacío.");
        }

        String[] partes = limiteTiempo.split(":");
        if (partes.length != 2) {
            throw new IllegalArgumentException("El límite de tiempo debe tener un formato de hora válido (HH:mm).");
        }

        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        return Duration.ofHours(horas).plusMinutes(minutos);
    }

    private static void validarPedido(Collection<Integer> itemsProductoCantidad) {
        if(!itemsProductoCantidad.stream().allMatch(i -> i > 0))
            throw new IllegalArgumentException("Las cantidades ingresadas deben ser enteros mayores a 0");
    }
}
