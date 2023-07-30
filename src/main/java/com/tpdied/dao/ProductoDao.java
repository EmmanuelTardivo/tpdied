package com.tpdied.dao;

import com.tpdied.models.Producto;

import jakarta.persistence.EntityManager;

public class ProductoDao extends AbstractDao<Producto>{
    public ProductoDao(EntityManager entityManager) {
        super(entityManager);
        setClase(Producto.class);
    }
}
