package com.tpdied.forms;

import com.tpdied.dto.ProductoDTO;

/**
 * Clase de utilidad para validar y convertir datos relacionados con objetos Producto.
 */
public class ProductoForm {

    /**
     * Valida y convierte los datos proporcionados en un objeto ProductoDTO.
     *
     * @param nombre      El nombre del Producto.
     * @param descripcion La descripción del Producto.
     * @param precio      El precio del Producto como un valor numérico en formato de cadena.
     * @param peso        El peso del Producto como un valor numérico en formato de cadena.
     * @return Objeto ProductoDTO con los datos validados.
     * @throws IllegalArgumentException Si alguno de los datos proporcionados es inválido.
     */
    public static ProductoDTO validarProducto(String nombre, String descripcion, String precio, String peso) {
        validarNombre(nombre);
        validarDescripcion(descripcion);
        double pesoDouble = validarPeso(peso);
        double precioDouble = validarPrecio(precio);

        ProductoDTO result = new ProductoDTO();
        result.setNombre(nombre);
        result.setDescripcion(descripcion);
        result.setPeso(pesoDouble);
        result.setPrecio(precioDouble);
        return result;
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 255) {
            throw new IllegalArgumentException("El nombre es inválido.");
        }

        if (!nombre.matches("^[a-zA-Z0-9][a-zA-Z0-9\\s]*[a-zA-Z0-9]$|^[a-zA-Z0-9]$")) {
            throw new IllegalArgumentException("El nombre contiene caracteres no permitidos.");
        }
    }

    private static void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty() || descripcion.length() > 255) {
            throw new IllegalArgumentException("La descripción es inválida.");
        }
    }

    private static double validarPeso(String peso) {
        if (peso == null) {
            throw new IllegalArgumentException("El peso no puede ser nulo.");
        }

        try {
            double pesoDouble = Double.parseDouble(peso);
            if (pesoDouble <= 0.0) {
                throw new IllegalArgumentException("El peso debe ser un número positivo mayor a 0.");
            }
            return pesoDouble;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El peso debe ser un número válido.");
        }
    }

    private static double validarPrecio(String precio) {
        if (precio == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo.");
        }

        try {
            double precioDouble = Double.parseDouble(precio);
            if (precioDouble <= 0.0) {
                throw new IllegalArgumentException("El precio debe ser un número positivo mayor a 0.");
            }
            return precioDouble;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un número válido.");
        }
    }
}
