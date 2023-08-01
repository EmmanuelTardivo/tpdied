package com.tpdied.forms;

import com.tpdied.dto.RutaDTO;
import com.tpdied.dto.SucursalDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class RutaFormTest {

    @Test
    public void testValidarRutaDatosValidos() {
        // Arrange
        String capacidadEnKilos = "500.0";
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = "04:30";
        Boolean estado = true;

        // Act
        RutaDTO result = RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado);

        // Assert
        assertNotNull(result);
        assertEquals(Double.parseDouble(capacidadEnKilos), result.getCapacidadEnKilos());
        assertEquals(sucursalOrigen, result.getSucursalOrigen());
        assertEquals(sucursalDestino, result.getSucursalDestino());
        assertEquals(LocalTime.parse(duracionViaje), result.getDuracionViaje());
        assertEquals(estado, result.getEstado());
    }

    @Test
    public void testValidarRutaCapacidadEnKilosNula() {
        // Arrange
        String capacidadEnKilos = null;
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = "04:30";
        Boolean estado = true;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado)
        );
    }

    @Test
    public void testValidarRutaCapacidadEnKilosVacia() {
        // Arrange
        String capacidadEnKilos = "   ";
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = "04:30";
        Boolean estado = true;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado)
        );
    }

    @Test
    public void testValidarRutaCapacidadEnKilosInvalida() {
        // Arrange
        String capacidadEnKilos = "500.0x";
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = "04:30";
        Boolean estado = true;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado)
        );
    }

    @Test
    public void testValidarRutaCapacidadEnKilosNegativa() {
        // Arrange
        String capacidadEnKilos = "-500.0";
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = "04:30";
        Boolean estado = true;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado)
        );
    }

    @Test
    public void testValidarRutaDuracionViajeNula() {
        // Arrange
        String capacidadEnKilos = "500.0";
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = null;
        Boolean estado = true;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado)
        );
    }

    @Test
    public void testValidarRutaDuracionViajeVacia() {
        // Arrange
        String capacidadEnKilos = "500.0";
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = "   ";
        Boolean estado = true;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado)
        );
    }

    @Test
    public void testValidarRutaDuracionViajeInvalida() {
        // Arrange
        String capacidadEnKilos = "500.0";
        SucursalDTO sucursalOrigen = new SucursalDTO();
        sucursalOrigen.setId(1);
        SucursalDTO sucursalDestino = new SucursalDTO();
        sucursalDestino.setId(2);
        String duracionViaje = "4:30";
        Boolean estado = true;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                RutaForm.validarRuta(capacidadEnKilos, sucursalOrigen, sucursalDestino, duracionViaje, estado)
        );
    }
}
