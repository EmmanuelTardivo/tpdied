package com.tpdied.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManagerFactory factory;

	private EntityManagerUtil() {
	}

	public static EntityManager getEntityManager() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("tpdied");
		return factory.createEntityManager();
	}

	public static void closeEntityManagerFactory() {
		if (factory != null) {
			factory.close();
		}
	}

	public static void limpiarDB() {
		EntityManager entityManager = getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			entityManager.createNativeQuery("DELETE FROM item_orden").executeUpdate();
			entityManager.createNativeQuery("DELETE FROM orden_de_provision").executeUpdate();
			entityManager.createNativeQuery("DELETE FROM stock_sucursal").executeUpdate();
			entityManager.createNativeQuery("DELETE FROM producto").executeUpdate();
			entityManager.createNativeQuery("DELETE FROM ruta").executeUpdate();
			entityManager.createNativeQuery("DELETE FROM sucursal").executeUpdate();

			entityManager.createNativeQuery("ALTER TABLE producto AUTO_INCREMENT = 1").executeUpdate();
			entityManager.createNativeQuery("ALTER TABLE ruta AUTO_INCREMENT = 1").executeUpdate();
			entityManager.createNativeQuery("ALTER TABLE orden_de_provision AUTO_INCREMENT = 1").executeUpdate();
			entityManager.createNativeQuery("ALTER TABLE sucursal AUTO_INCREMENT = 1").executeUpdate();

			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

}