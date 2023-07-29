package com.tpdied.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tpdied.dao.ProductoDao;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.mappers.ProductoMapper;
import com.tpdied.models.Producto;

public class ProductoController {

    private ProductoDao productoDao;

    public ProductoController() {
        productoDao = new ProductoDao();
    }

    public List<ProductoDTO> getAllEntities() {
        List<Producto> productos = productoDao.getAll();
        return productos.stream()
                .map(ProductoMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductoDTO getEntityById(int id) {
        Optional<Producto> producto = productoDao.getById(id);
        return producto.isPresent() ? ProductoMapper.toDto(producto.get()) : null;
    }

    public void addEntity(ProductoDTO dto) {
        Producto producto = (ProductoMapper.toEntity(dto));
        productoDao.save(producto);
    }

    public void updateEntity(ProductoDTO dto) {
        Producto producto = (ProductoMapper.toEntity(dto));
        productoDao.update(producto);
    }

    public void deleteEntity(ProductoDTO dto) {
        Producto producto = (ProductoMapper.toEntity(dto));
        productoDao.delete(producto);
    }

}
