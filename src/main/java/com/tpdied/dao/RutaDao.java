package com.tpdied.dao;

import com.tpdied.models.Ruta;

import jakarta.persistence.EntityManager;

public class RutaDao extends AbstractDao<Ruta>{
    public RutaDao(EntityManager entityManager) {
        super(entityManager);
        setClase(Ruta.class);
    }
}
