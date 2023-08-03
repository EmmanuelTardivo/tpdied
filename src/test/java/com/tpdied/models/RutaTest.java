package com.tpdied.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RutaTest {

    private Ruta ruta;
    private Sucursal sucursalOrigenMock;
    private Sucursal sucursalDestinoMock;

    @BeforeEach
    void setUp() {
        ruta = new Ruta();

        // Crear mocks para las sucursales
        sucursalOrigenMock = new Sucursal();
        sucursalOrigenMock.setNombre("Sucursal Origen Mock");
        sucursalDestinoMock = new Sucursal();
        sucursalDestinoMock.setNombre("Sucursal Destino Mock");

        // Configurar la ruta
        ruta.setId(1);
        ruta.setSucursalOrigen(sucursalOrigenMock);
        ruta.setSucursalDestino(sucursalDestinoMock);
        ruta.setCapacidadEnKilos(1000.0);
        ruta.setDuracionViaje(Duration.ofHours(2).plusMinutes(30));
        ruta.setEstado(true);
    }

    @Test
    void testGetId() {
        assertEquals(1, ruta.getId());
    }

    @Test
    void testGetSucursalOrigen() {
        assertEquals(sucursalOrigenMock, ruta.getSucursalOrigen());
    }

    @Test
    void testGetSucursalDestino() {
        assertEquals(sucursalDestinoMock, ruta.getSucursalDestino());
    }

    @Test
    void testGetCapacidadEnKilos() {
        assertEquals(1000.0, ruta.getCapacidadEnKilos(), 0.001);
    }

    /* @Test
    void testGetDuracionViaje() {
        assertEquals(LocalTime.of(2, 30), ruta.getDuracionViaje());
    } */

    @Test
    void testGetEstado() {
        assertTrue(ruta.getEstado());
    }

    @Test
    void testSetEstado() {
        ruta.setEstado(false);
        assertFalse(ruta.getEstado());
    }

    @Test
    void testEstadoToStringOperativo() {
        assertEquals("Operativo", ruta.estadoToString());
    }

    @Test
    void testEstadoToStringNoOperativo() {
        ruta.setEstado(false);
        assertEquals("No operativo", ruta.estadoToString());
    }

    @Test
    void testToString() {
        String expectedString = "Ruta [id=1, sucursalOrigen=Sucursal Origen Mock, sucursalDestino=Sucursal Destino Mock, capacidadEnKilos=1000.0, duracionViaje=02:30, estado=Operativo]";
        assertEquals(expectedString, ruta.toString());
    }

    @Test
    void testGetEliminado() {
        assertFalse(ruta.getEliminado());
    }

    @Test
    void testSetEliminado() {
        ruta.setEliminado(true);
        assertTrue(ruta.getEliminado());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(ruta, ruta);
    }

    @Test
    void testEqualsEqualObjects() {
        Ruta otraRuta = crearRutaTest();
        assertEquals(ruta, otraRuta);
    }

    @Test
    void testEqualsDifferentObjects() {
        Ruta otraRuta = new Ruta();
        otraRuta.setId(2);
        otraRuta.setSucursalOrigen(new Sucursal());
        otraRuta.setSucursalDestino(new Sucursal());
        otraRuta.setCapacidadEnKilos(2000.0);
        otraRuta.setDuracionViaje(Duration.ofHours(3));
        otraRuta.setEstado(false);

        assertNotEquals(ruta, otraRuta);
    }

    @Test
    void testHashCodeEqualObjects() {
        Ruta otraRuta = crearRutaTest();
        assertEquals(ruta.hashCode(), otraRuta.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        Ruta otraRuta = new Ruta();
        otraRuta.setId(2);
        otraRuta.setSucursalOrigen(new Sucursal());
        otraRuta.setSucursalDestino(new Sucursal());
        otraRuta.setCapacidadEnKilos(2000.0);
        otraRuta.setDuracionViaje(Duration.ofHours(3));
        otraRuta.setEstado(false);

        assertNotEquals(ruta.hashCode(), otraRuta.hashCode());
    }

    private Ruta crearRutaTest() {
        Ruta otraRuta = new Ruta();
        otraRuta.setId(1);
        otraRuta.setSucursalOrigen(sucursalOrigenMock);
        otraRuta.setSucursalDestino(sucursalDestinoMock);
        otraRuta.setCapacidadEnKilos(1000.0);
        otraRuta.setDuracionViaje(Duration.ofHours(2).plusMinutes(30));
        otraRuta.setEstado(true);
        return otraRuta;
    }
}
