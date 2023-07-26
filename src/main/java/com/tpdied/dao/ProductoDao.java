package com.tpdied.dao;

import com.tpdied.models.Producto;

public class ProductoDao extends AbstractDao<Producto>{
    public ProductoDao() {
        setClase(Producto.class);
    }
}
