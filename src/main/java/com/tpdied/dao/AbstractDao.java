package com.tpdied.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.tpdied.models.Eliminable;
import com.tpdied.util.EntityManagerUtil;

public abstract class AbstractDao<T extends Eliminable> implements Dao<T> {

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();
	private Class<T> clase;

	@Override
	public Optional<T> getById(int id) {
		return Optional.ofNullable(entityManager.find(clase, id));
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<T> getAll() {
		String qlString = "FROM " + clase.getName();
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
