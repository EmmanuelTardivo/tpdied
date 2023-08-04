package com.tpdied.dao;

import java.time.Duration;
import java.util.List;

import com.tpdied.models.Ruta;
import com.tpdied.models.Sucursal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RutaDao extends AbstractDao<Ruta> {

    public RutaDao(EntityManager entityManager) {
        super(entityManager);
        setClase(Ruta.class);
    }

    public List<Ruta> getBySucursalOrigen(Sucursal origen) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = false AND r.sucursalOrigen = :origen";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("origen", origen);
        return query.getResultList();
    }

    public List<Ruta> getBySucursalDestino(Sucursal destino) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = false AND r.sucursalDestino = :destino";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("destino", destino);
        return query.getResultList();
    }

    public List<Ruta> getByDuracionViaje(Duration duracionViaje) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = false AND r.duracionViaje = :duracionViaje";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("duracionViaje", duracionViaje);
        return query.getResultList();
    }

    public List<Ruta> getByCapacidadEnKilos(Double capacidadEnKilos) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = false AND r.capacidadEnKilos = :capacidadEnKilos";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("capacidadEnKilos", capacidadEnKilos);
        return query.getResultList();
    }

    public List<Ruta> getByEstado(Boolean estado) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = false AND r.estado = :estado";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
