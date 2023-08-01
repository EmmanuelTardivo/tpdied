package com.tpdied.dao;

import java.time.LocalTime;
import java.util.List;

import com.tpdied.models.Sucursal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class SucursalDao extends AbstractDao <Sucursal>{

    public SucursalDao(EntityManager entityManager) {
        super(entityManager);
        setClase(Sucursal.class);
    }
    
    public Sucursal getByName(String name) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = false AND s.nombre = :name";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Sucursal> getByHorarioApertura(LocalTime horarioApertura) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = false AND s.horaApertura = :horarioApertura";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("horarioApertura", horarioApertura);
        return query.getResultList();
    }

    public List<Sucursal> getByHorarioCierre(LocalTime horarioCierre) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = false AND s.horaCierre = :horarioCierre";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("horarioCierre", horarioCierre);
        return query.getResultList();
    }

    public List<Sucursal> getByEstado(Boolean estado) {
        String qlString = "SELECT s FROM Sucursal s WHERE s.eliminado = false AND s.estado = :estado";
        TypedQuery<Sucursal> query = getEntityManager().createQuery(qlString, Sucursal.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
