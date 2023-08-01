package com.tpdied.forms;

import com.tpdied.dto.ProductoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoFormTest {

    @Test
    public void testValidarProductoDatosValidos() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción del producto";
        String precio = "50.99";
        String peso = "0.5";

        // Act
        ProductoDTO result = ProductoForm.validarProducto(nombre, descripcion, precio, peso);

        // Assert
        assertNotNull(result);
        assertEquals(nombre, result.getNombre());
        assertEquals(descripcion, result.getDescripcion());
        assertEquals(Double.parseDouble(precio), result.getPrecio());
        assertEquals(Double.parseDouble(peso), result.getPeso());
    }

    @Test
    public void testValidarProductoNombreNulo() {
        // Arrange
        String nombre = null;
        String descripcion = "Descripción del producto";
        String precio = "50.99";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoNombreVacio() {
        // Arrange
        String nombre = "   ";
        String descripcion = "Descripción del producto";
        String precio = "50.99";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoNombreInvalido() {
        // Arrange
        String nombre = "Producto 1#";
        String descripcion = "Descripción del producto";
        String precio = "50.99";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoDescripcionNula() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = null;
        String precio = "50.99";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoDescripcionVacia() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "   ";
        String precio = "50.99";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoDescripcionLarga() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción muy larga".repeat(20);
        String precio = "50.99";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoPrecioNulo() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción del producto";
        String precio = null;
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoPrecioInvalido() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción del producto";
        String precio = "50.99x";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoPrecioNegativo() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción del producto";
        String precio = "-50.99";
        String peso = "0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoPesoNulo() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción del producto";
        String precio = "50.99";
        String peso = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoPesoInvalido() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción del producto";
        String precio = "50.99";
        String peso = "0.5x";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }

    @Test
    public void testValidarProductoPesoNegativo() {
        // Arrange
        String nombre = "Producto 1";
        String descripcion = "Descripción del producto";
        String precio = "50.99";
        String peso = "-0.5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                ProductoForm.validarProducto(nombre, descripcion, precio, peso)
        );
    }
}
