package com.tpdied.mappers;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Producto> toEntity(List<ProductoDTO> productosDto) {
        return productosDto.stream()
                .map(ProductoMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<ProductoDTO> toDto(List<Producto> productos) {
        return productos.stream()
                .map(ProductoMapper::toDto)
                .collect(Collectors.toList());
    }
}
