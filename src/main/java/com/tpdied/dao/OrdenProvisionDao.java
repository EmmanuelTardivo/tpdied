package com.tpdied.dao;

import com.tpdied.models.OrdenProvision;

import jakarta.persistence.EntityManager;

public class OrdenProvisionDao extends AbstractDao<OrdenProvision>{
    public OrdenProvisionDao(EntityManager entityManager) {
        super(entityManager);
        setClase(OrdenProvision.class);
    }
}
