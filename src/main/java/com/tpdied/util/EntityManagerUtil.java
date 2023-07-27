package com.tpdied.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManagerFactory factory;

	private EntityManagerUtil(){
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
    /* public static void main(String[] args) {
		EntityManager manager = EntityManagerUtil.getEntityManager();
		System.out.println("EntityManager class ==> " + manager.getClass().getCanonicalName());
	} */
}