package com.luizjacomn.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("gmapPU");

	public static EntityManager getEM() {
		return emf.createEntityManager();
	}
}
