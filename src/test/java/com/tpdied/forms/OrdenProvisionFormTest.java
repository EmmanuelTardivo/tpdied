package com.tpdied.forms;

import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrdenProvisionFormTest {

    @Test
    public void testValidarOrdenProvisionDatosValidos() {
        // Arrange
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(1);
        String limiteHoras = "02:00";
        Map<ProductoDTO, Integer> itemsProductoCantidad = new HashMap<>();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        itemsProductoCantidad.put(productoDTO, 5);

        // Act
        OrdenProvisionDTO result = OrdenProvisionForm.validarOrdenProvision(sucursalDestino, limiteHoras,
                itemsProductoCantidad);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getFechaOrden());
        assertEquals(LocalTime.parse(limiteHoras), result.getLimiteTiempo());
        assertEquals(sucursalDestino, result.getSucursalDestino());
        assertEquals(itemsProductoCantidad, result.getItemsProductoCantidad());
    }

    @Test
    public void testValidarOrdenProvisionLimiteHorasNulo() {
        // Arrange
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(1);
        String limiteHoras = null;
        Map<ProductoDTO, Integer> itemsProductoCantidad = new HashMap<>();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        itemsProductoCantidad.put(productoDTO, 5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> OrdenProvisionForm.validarOrdenProvision(sucursalDestino,
                limiteHoras, itemsProductoCantidad));
    }

    @Test
    public void testValidarOrdenProvisionLimiteHorasVacio() {
        // Arrange
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(1);
        String limiteHoras = "   ";
        Map<ProductoDTO, Integer> itemsProductoCantidad = new HashMap<>();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        itemsProductoCantidad.put(productoDTO, 5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> OrdenProvisionForm.validarOrdenProvision(sucursalDestino,
                limiteHoras, itemsProductoCantidad));
    }

    @Test
    public void testValidarOrdenProvisionLimiteHorasNoEntero() {
        // Arrange
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(1);
        String limiteHoras = "2.5";
        Map<ProductoDTO, Integer> itemsProductoCantidad = new HashMap<>();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        itemsProductoCantidad.put(productoDTO, 5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> OrdenProvisionForm.validarOrdenProvision(sucursalDestino,
                limiteHoras, itemsProductoCantidad));
    }

    @Test
    public void testValidarOrdenProvisionLimiteHorasNegativo() {
        // Arrange
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(1);
        String limiteHoras = "-2";
        Map<ProductoDTO, Integer> itemsProductoCantidad = new HashMap<>();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        itemsProductoCantidad.put(productoDTO, 5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> OrdenProvisionForm.validarOrdenProvision(sucursalDestino,
                limiteHoras, itemsProductoCantidad));
    }
}
