package com.tpdied.dao;

import java.util.List;

import com.tpdied.models.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ProductoDao extends AbstractDao<Producto>{
    public ProductoDao(EntityManager entityManager) {
        super(entityManager);
        setClase(Producto.class);
    }

    public Producto getByName(String name) {
        String qlString = "SELECT p FROM Producto p WHERE p.eliminado = false AND p.nombre = :name";
        TypedQuery<Producto> query = getEntityManager().createQuery(qlString, Producto.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Producto> getByDescripcion(String descripcion) {
        String qlString = "SELECT p FROM Producto p WHERE p.eliminado = false AND p.descripcion = :descripcion";
        TypedQuery<Producto> query = getEntityManager().createQuery(qlString, Producto.class);
        query.setParameter("descripcion", descripcion);
        return query.getResultList();
    }

    public List<Producto> getByPrecio(Double precio) {
        String qlString = "SELECT p FROM Producto p WHERE p.eliminado = false AND p.precio = :precio";
        TypedQuery<Producto> query = getEntityManager().createQuery(qlString, Producto.class);
        query.setParameter("precio", precio);
        return query.getResultList();
    }

    public List<Producto> getByPeso(Double peso) {
        String qlString = "SELECT p FROM Producto p WHERE p.eliminado = false AND p.peso = :peso";
        TypedQuery<Producto> query = getEntityManager().createQuery(qlString, Producto.class);
        query.setParameter("peso", peso);
        return query.getResultList();
    }
}
