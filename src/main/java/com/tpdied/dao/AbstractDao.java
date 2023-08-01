package com.tpdied.dao;

import java.util.List;
import java.util.function.Consumer;

import com.tpdied.models.Eliminable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

/**
 * Esta clase abstracta proporciona una implementación genérica para un DAO
 * básico que opera sobre entidades eliminables.
 *
 * @param <T> Tipo de la entidad eliminable.
 */
public abstract class AbstractDao<T extends Eliminable> implements Dao<T> {

	private final EntityManager entityManager;
	private Class<T> clase;

	/**
	 * Crea una nueva instancia de AbstractDao con el EntityManager especificado.
	 *
	 * @param entityManager El EntityManager que se utilizará para acceder a la base
	 *                      de datos.
	 */
	public AbstractDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Obtiene el EntityManager asociado con este DAO.
	 *
	 * @return El EntityManager utilizado por este DAO para acceder a la base de
	 *         datos.
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Obtiene una entidad eliminable por su ID desde la base de datos.
	 *
	 * @param id El ID de la entidad a buscar.
	 * @return La entidad encontrada, o null si no se encuentra.
	 */
	@Override
	public T getById(int id) {
		return entityManager.find(clase, id);
	}

	/**
	 * Obtiene una lista de todas las entidades con estado eliminado = false en la
	 * base de datos.
	 *
	 * @return Una lista de las entidades no eliminadas.
	 */
	@Override
	public List<T> getAll() {
		String qlString = "FROM " + clase.getName() + " WHERE eliminado = false";
		TypedQuery<T> query = entityManager.createQuery(qlString, clase);
		return query.getResultList();
	}

	/**
	 * Guarda una entidad en la base de datos. Si la entidad ya existe en la base de
	 * datos (basado en el método equals)
	 * pero tiene estado eliminado = true, este se actualiza a false.
	 * Si la entidad no existe en la base de datos, se inserta como una nueva
	 * entidad.
	 *
	 * @param t La entidad a guardar.
	 */
	@Override
	public void save(T t) {
		T equal = findEqualEliminado(t);
		if (equal != null) {
			equal.setEliminado(false);
			update(equal);
		} else {
			executeInsideTransaction(entityManager -> entityManager.persist(t));
		}
	}

	/**
	 * Actualiza el estado de una entidad en la base de datos.
	 *
	 * @param t La entidad a actualizar.
	 */
	@Override
	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	/**
	 * Elimina una entidad estableciendo su estado eliminado a true y la actualiza
	 * en la base de datos.
	 *
	 * @param t La entidad a eliminar.
	 */
	@Override
	public void delete(T t) {
		t.setEliminado(true);
		update(t);
	}

	/**
	 * Busca una entidad equivalente eliminada en la base de datos basándose en el
	 * método equals de la entidad.
	 *
	 * @param entity La entidad a buscar.
	 * @return La entidad encontrada, o null si no se encuentra.
	 */
	@Override
	public T findEqualEliminado(T entity) {
		return getAllEliminados().stream()
				.filter(dbEntity -> dbEntity.equals(entity))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Establece la clase de la entidad que se utilizará en las operaciones del DAO.
	 *
	 * @param clase La clase de la entidad.
	 */
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

	private List<T> getAllEliminados() {
		String qlString = "FROM " + clase.getName() + " WHERE eliminado = true";
		TypedQuery<T> query = entityManager.createQuery(qlString, clase);
		return query.getResultList();
	}
}
