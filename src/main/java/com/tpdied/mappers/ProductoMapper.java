package com.tpdied.mappers;

import com.tpdied.dto.ProductoDTO;
import com.tpdied.models.Producto;

public class ProductoMapper {

    /**
     * Produce una instancia de Producto con los datos del DTO arg
     * 
     * @param productoDTO
     * @return Producto con datos de DTO
     */
    public static Producto toEntity(ProductoDTO productoDTO) {

        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPeso(productoDTO.getPeso());
        producto.setPrecio(productoDTO.getPrecio());

        return producto;
    }

    /**
     * Produce un DTO con los datos de un objeto Producto
     * 
     * @param producto
     * @return DTO con datos de obj. Producto
     */
    public static ProductoDTO toDto(Producto producto) {

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPeso(producto.getPeso());
        productoDTO.setPrecio(producto.getPrecio());

        return productoDTO;
    }

}
