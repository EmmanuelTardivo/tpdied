package com.tpdied.dao;

import com.tpdied.models.Sucursal;

import jakarta.persistence.EntityManager;

public class SucursalDao extends AbstractDao <Sucursal>{
    public SucursalDao(EntityManager entityManager) {
        super(entityManager);
        setClase(Sucursal.class);
    }
}
