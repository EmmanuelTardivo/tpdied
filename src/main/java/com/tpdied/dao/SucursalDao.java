package com.tpdied.dao;

import java.time.LocalTime;
import java.util.List;

import com.tpdied.models.Sucursal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class SucursalDao extends AbstractDao <Sucursal>{

    public SucursalDao(EntityManager entityManager) {
        super(entityManager);
        setClase(Sucursal.class);
    }

    public Sucursal getByName(String name) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = 0 AND s.nombre_sucursal = :name";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public List<Sucursal> getByHorarioApertura(LocalTime horarioApertura) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = 0 AND s.hora_apertura = :horarioApertura";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("horarioApertura", horarioApertura);
        return query.getResultList();
    }

    public List<Sucursal> getByHorarioCierre(LocalTime horarioCierre) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = 0 AND s.hora_cierre = :horarioCierre";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("horarioCierre", horarioCierre);
        return query.getResultList();
    }

    public List<Sucursal> getByOperativa(Boolean operativa) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = 0 AND s.estado_sucursal = :operativa";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("operativa", operativa);
        return query.getResultList();
    }
}
