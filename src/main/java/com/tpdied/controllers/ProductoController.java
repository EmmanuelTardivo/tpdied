package com.tpdied.controllers;

import java.util.List;

import com.tpdied.dao.ProductoDao;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.mappers.ProductoMapper;
import com.tpdied.models.Producto;

import jakarta.persistence.EntityManager;

public class ProductoController {

    private ProductoDao productoDao;

    public ProductoController(EntityManager entityManager) {
        productoDao = new ProductoDao(entityManager);
    }

    public List<ProductoDTO> getAllProductos() {
        return ProductoMapper.toDto(productoDao.getAll());
    }

    public ProductoDTO getProductoById(int id) {
        Producto producto = productoDao.getById(id);
        return producto != null ? ProductoMapper.toDto(producto) : null;
    }

    // !!!
    public ProductoDTO getProductoByName(String name) {
        Producto producto = productoDao.getByName(name);
        return producto != null ? ProductoMapper.toDto(producto) : null;

    }

    public List<ProductoDTO> getProductosByName(String name) {
        return ProductoMapper.toDto(productoDao.getProductosByName(name));
    }

    public List<ProductoDTO> getProductosByDescripcion(String descripcion) {
        return ProductoMapper.toDto(productoDao.getByDescripcion(descripcion));
    }

    public List<ProductoDTO> getProductosByPrecio(Double precio) {
        return ProductoMapper.toDto(productoDao.getByPrecio(precio));
    }

    public List<ProductoDTO> getProductosByPeso(Double peso) {
        return ProductoMapper.toDto(productoDao.getByPeso(peso));
    }

    public void addProducto(ProductoDTO dto) {
        Producto producto = (ProductoMapper.toEntity(dto));
        if (productoDao.getAll().contains(producto))
            throw new IllegalArgumentException("Ya existe el producto.");
        productoDao.save(producto);
    }

    public void updateProducto(ProductoDTO dto) {
        Producto producto = (ProductoMapper.toEntity(dto));
        productoDao.update(producto);
    }

    public void deleteProducto(ProductoDTO dto) {
        Producto producto = (ProductoMapper.toEntity(dto));
        productoDao.delete(producto);
    }

}
