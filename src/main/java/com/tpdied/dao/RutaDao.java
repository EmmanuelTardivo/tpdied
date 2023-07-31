package com.tpdied.dao;

import java.time.LocalTime;
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
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = 0 AND r.id_origen = :origen";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("origen", origen.getId());
        return query.getResultList();
    }

    public List<Ruta> getBySucursalDestino(Sucursal destino) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = 0 AND r.id_destino = :destino";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("destino", destino);
        return query.getResultList();
    }

    public List<Ruta> getByDuracionViaje(LocalTime duracionViaje) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = 0 AND r.duracion_viaje = :duracionViaje";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("duracionViaje", duracionViaje);
        return query.getResultList();
    }

    public List<Ruta> getByCapacidadEnKilos(Double capacidadEnKilos) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = 0 AND r.capacidad_en_kilos = :capacidadEnKilos";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("capacidadEnKilos", capacidadEnKilos);
        return query.getResultList();
    }

    public List<Ruta> getByOperativa(Boolean operativa) {
        String qlString = "SELECT r FROM Ruta r WHERE r.eliminado = 0 AND r.estado_ruta = :operativa";
        TypedQuery<Ruta> query = getEntityManager().createQuery(qlString, Ruta.class);
        query.setParameter("operativa", operativa);
        return query.getResultList();
    }
}
