package com.tpdied.forms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.tpdied.dto.SucursalDTO;

import java.time.LocalTime;

public class SucursalFormTest {

    @Test
    public void testValidarSucursal_ValidInput() {
        String nombre = "Sucursal A";
        String horaApertura = "08:00";
        String horaCierre = "18:00";
        Boolean estado = true;

        SucursalDTO sucursalDTO = SucursalForm.validarSucursal(nombre, horaApertura, horaCierre, estado);

        assertNotNull(sucursalDTO);
        assertEquals(nombre, sucursalDTO.getNombre());
        assertEquals(LocalTime.parse(horaApertura), sucursalDTO.getHoraApertura());
        assertEquals(LocalTime.parse(horaCierre), sucursalDTO.getHoraCierre());
        assertEquals(estado, sucursalDTO.getEstado());
    }

    @Test
    public void testValidarSucursal_InvalidNombre_Null() {
        String nombre = null;
        String horaApertura = "08:00";
        String horaCierre = "18:00";
        Boolean estado = true;

        assertThrows(IllegalArgumentException.class, () -> {
            SucursalForm.validarSucursal(nombre, horaApertura, horaCierre, estado);
        });
    }

    @Test
    public void testValidarSucursal_InvalidNombre_Empty() {
        String nombre = "";
        String horaApertura = "08:00";
        String horaCierre = "18:00";
        Boolean estado = true;

        assertThrows(IllegalArgumentException.class, () -> {
            SucursalForm.validarSucursal(nombre, horaApertura, horaCierre, estado);
        });
    }

    @Test
    public void testValidarSucursal_InvalidNombre_TooLong() {
        String nombre = "Sucursal A con un nombre demasiado largo que supera los 255 caracteres permitidos".repeat(4);
        String horaApertura = "08:00";
        String horaCierre = "18:00";
        Boolean estado = true;

        assertThrows(IllegalArgumentException.class, () -> {
            SucursalForm.validarSucursal(nombre, horaApertura, horaCierre, estado);
        });
    }

    @Test
    public void testValidarSucursal_InvalidNombre_ContainsInvalidCharacters() {
        String nombre = "Sucursal A con caracteres inválidos!";
        String horaApertura = "08:00";
        String horaCierre = "18:00";
        Boolean estado = true;

        assertThrows(IllegalArgumentException.class, () -> {
            SucursalForm.validarSucursal(nombre, horaApertura, horaCierre, estado);
        });
    }

    @Test
    public void testValidarSucursal_InvalidHoraApertura_InvalidFormat() {
        String nombre = "Sucursal A";
        String horaApertura = "08:3200"; // Formato de hora inválido
        String horaCierre = "18:00";
        Boolean estado = true;

        assertThrows(IllegalArgumentException.class, () -> {
            SucursalForm.validarSucursal(nombre, horaApertura, horaCierre, estado);
        });
    }

    @Test
    public void testValidarSucursal_InvalidHoraCierre_AntesDeHoraApertura() {
        String nombre = "Sucursal A";
        String horaApertura = "08:00";
        String horaCierre = "07:00"; // Hora de cierre antes de la hora de apertura
        Boolean estado = true;

        assertThrows(IllegalArgumentException.class, () -> {
            SucursalForm.validarSucursal(nombre, horaApertura, horaCierre, estado);
        });
    }
}
