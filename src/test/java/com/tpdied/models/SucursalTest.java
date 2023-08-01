package com.tpdied.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SucursalTest {
    @Test
    void testEqualsSameObject() {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal A");

        boolean result = sucursal.equals(sucursal);

        Assertions.assertTrue(result);
    }

    @Test
    void testEqualsNull() {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal A");

        boolean result = sucursal.equals(null);

        Assertions.assertFalse(result);
    }

    @Test
    void testEqualsDifferentClass() {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal A");

        boolean result = sucursal.equals(new Object());

        Assertions.assertFalse(result);
    }

    @Test
    void testEqualsSameNombre() {
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setId(1);
        sucursal1.setNombre("Sucursal A");

        Sucursal sucursal2 = new Sucursal();
        sucursal2.setNombre("Sucursal A");
        sucursal2.setId(2);

        boolean result = sucursal1.equals(sucursal2);

        Assertions.assertTrue(result);
    }

    @Test
    void testEqualsDifferentNombre() {
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setNombre("Sucursal A");

        Sucursal sucursal2 = new Sucursal();
        sucursal2.setNombre("Sucursal B");

        boolean result = sucursal1.equals(sucursal2);

        Assertions.assertFalse(result);
    }
}
