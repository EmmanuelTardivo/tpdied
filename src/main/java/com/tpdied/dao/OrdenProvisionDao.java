package com.tpdied.dao;

import java.util.List;

import com.tpdied.models.OrdenProvision;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class OrdenProvisionDao extends AbstractDao<OrdenProvision>{
    public OrdenProvisionDao(EntityManager entityManager) {
        super(entityManager);
        setClase(OrdenProvision.class);
    }

    public List<OrdenProvision> getOrdenesPendientes(){
        String qlString = "SELECT o FROM OrdenProvision o WHERE o.eliminado = false AND o.estado = 'PENDIENTE'";
        TypedQuery<OrdenProvision> query = getEntityManager().createQuery(qlString, OrdenProvision.class);
        return query.getResultList();
    }
}
