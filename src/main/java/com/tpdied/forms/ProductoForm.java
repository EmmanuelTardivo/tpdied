package com.tpdied.forms;

import com.tpdied.dto.ProductoDTO;

public class ProductoForm {

    /**
     * Valida los datos del Producto conformado por los args String
     * 
     * @param nombre      String
     * @param descripcion String
     * @param peso        String
     * @param precio      String
     * @return ProductoDTO con los datos transformados de los String.
     * @throws Exception Si no valida algun campo de los dados.
     */
    public static ProductoDTO validarProducto(String nombre, String descripcion, String precio, String peso)
            throws Exception {

        if (!validarNombre(nombre)
                || !validarDescripcion(descripcion)
                || !validarPeso(peso)
                || !validarPrecio(precio))
            throw new Exception("Producto invalido. Por favor revisar.");

        ProductoDTO result = new ProductoDTO();
        result.setNombre(nombre);
        result.setDescripcion(descripcion);
        result.setPeso(Double.parseDouble(peso));
        result.setPrecio(Double.parseDouble(precio));
        return result;
    }

    /**
     * Verifica si el nombre del producto es valido
     * 
     * @param nombre String
     * @return false si nombre es vacio, nulo, contiene mas de 255 caracteres o esta
     *         compuesto de simbolos. En caso contrario true
     */
    private static boolean validarNombre(String nombre) {
        return nombre != null &&
                !nombre.trim().isEmpty() &&
                !(nombre.length() > 255) &&
                !nombre.matches("^[a-zA-Z0-9][a-zA-Z0-9\\s]*[a-zA-Z0-9]$");
    }

    /**
     * Verifica si la descripcion del producto es valida
     * 
     * @param descripcion String
     * @return false si descripcion es vacio, nulo o contiene mas de 255 caracteres
     */
    private static boolean validarDescripcion(String descripcion) {
        return descripcion != null &&
                !descripcion.trim().isEmpty() &&
                !(descripcion.length() > 255);
    }

    /**
     * Verifica que el peso del producto es valido
     * 
     * @param peso String
     * @return True si peso no es nulo, tiene formato de double y es positivo.
     *         False en caso contrario
     */
    private static boolean validarPeso(String peso) {

        if (peso == null)
            return false;

        try {
            return Double.parseDouble(peso) > 0.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica que el precio del producto es valido
     * 
     * @param precio String
     * @return True si precio no es nulo, tiene formato de double y es positivo.
     *         False en caso contrario
     */
    private static boolean validarPrecio(String precio) {

        if (precio == null)
            return false;

        try {
            return Double.parseDouble(precio) >= 1.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}