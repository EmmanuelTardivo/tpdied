package com.tpdied.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1);
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción del Producto Test");
        producto.setPrecio(100.0);
        producto.setPeso(0.5);
    }

    @Test
    void testGetId() {
        assertEquals(1, producto.getId());
    }

    @Test
    void testGetNombre() {
        assertEquals("Producto Test", producto.getNombre());
    }

    @Test
    void testGetDescripcion() {
        assertEquals("Descripción del Producto Test", producto.getDescripcion());
    }

    @Test
    void testGetPrecio() {
        assertEquals(100.0, producto.getPrecio(), 0.001);
    }

    @Test
    void testGetPeso() {
        assertEquals(0.5, producto.getPeso(), 0.001);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(producto, producto);
    }
    
    @Test
    void testEqualsNull() {
        assertFalse(producto.equals(null));
    }  

    @Test
    void testEqualsDifferentClass() {
        assertFalse(producto.equals(new Object()));
    }

    @Test
    void testEqualsObjNameNull() {
        Producto otroProducto = crearProductoEqual();
        producto.setNombre(null);
        otroProducto.setNombre(null);
        assertTrue(producto.equals(otroProducto));
    }

    @Test
    void testEqualsObjNameNotNull() {
        producto.setNombre(null);
        Producto otroProducto = crearProductoEqual(); 

        assertFalse(producto.equals(otroProducto));
    }

    @Test
    void testEqualsEqualObjects() {
        Producto otroProducto = crearProductoEqual();

        assertEquals(producto, otroProducto);
    }

    @Test
    void testEqualsDifferentObjects() {
        Producto otroProducto = creaProductoNotEqual();

        assertNotEquals(producto, otroProducto);
    }

    @Test
    void testHashCodeEqualObjects() {
        Producto otroProducto = crearProductoEqual();

        assertEquals(producto.hashCode(), otroProducto.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        Producto otroProducto = creaProductoNotEqual();

        assertNotEquals(producto.hashCode(), otroProducto.hashCode());
    }

    @Test
    void testHashCodeNullName() {
        producto.setNombre(null);

        assertEquals(31, producto.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Producto [id=1, nombre=Producto Test, descripcion=Descripción del Producto Test, precio=100.0, peso=0.5]";
        assertEquals(expectedString, producto.toString());
    }

    @Test
    void testGetEliminado() {
        assertFalse(producto.getEliminado());
    }

    @Test
    void testSetEliminado() {
        producto.setEliminado(true);
        assertTrue(producto.getEliminado());
    }

    private Producto crearProductoEqual() {
        Producto otroProducto = new Producto();
        otroProducto.setId(1);
        otroProducto.setNombre("Producto Test");
        otroProducto.setDescripcion("Descripción del Producto Test");
        otroProducto.setPrecio(100.0);
        otroProducto.setPeso(0.5);
        return otroProducto;
    }

    private Producto creaProductoNotEqual(){
        Producto otroProducto = new Producto();
        otroProducto.setId(2);
        otroProducto.setNombre("Otro Producto");
        otroProducto.setDescripcion("Descripción de Otro Producto");
        otroProducto.setPrecio(200.0);
        otroProducto.setPeso(0.75);
        return otroProducto;
    }
}
