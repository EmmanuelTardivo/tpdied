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
        String qlString = "SELECT o FROM orden_de_provision o WHERE o.eliminado = 0 AND o.estado_orden = 'PENDIENTE'";
        TypedQuery<OrdenProvision> query = getEntityManager().createQuery(qlString, OrdenProvision.class);
        return query.getResultList();
    }
}
