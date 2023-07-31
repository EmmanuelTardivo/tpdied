package com.tpdied.dao;

import java.util.List;
import java.util.function.Consumer;

import com.tpdied.models.Eliminable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public abstract class AbstractDao<T extends Eliminable> implements Dao<T> {

	private final EntityManager entityManager;
    private Class<T> clase;

    public AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public T getById(int id) {
		return entityManager.find(clase, id);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<T> getAll() {
		String qlString = "FROM " + clase.getName() + " WHERE eliminado = 0"; //revisar
		TypedQuery<T> query = entityManager.createQuery(qlString, clase);
		return query.getResultList();
	}

	@Override
	public void save(T t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	@Override
	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	@Override
	public void delete(T t) {
		t.setEliminado(true);
		update(t);
	}

	public void setClase(Class<T> clase) {
		this.clase = clase;
	}

	private void executeInsideTransaction(Consumer<EntityManager> action) {
    EntityTransaction tx = entityManager.getTransaction();
    try {
        tx.begin();
        action.accept(entityManager);
        tx.commit();
    } catch (RuntimeException e) {
        tx.rollback();
        throw e;
    }
}

	
	
}
