package com.luizjacomn.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static Session session = null;

	public static Session getSession() {
		try {
			Configuration config = new Configuration().configure();
			ServiceRegistry serviceRegistry = null;
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder.applySettings(config.getProperties());
			serviceRegistry = builder.build();
			SessionFactory factory = config.buildSessionFactory(serviceRegistry);
			session = factory.openSession();
		} catch (HibernateException e) {
			System.err.println(">>> ERRO AO TENTAR CRIAR A SESSAO! <<<");
		}
		return session;
	}

	public static void closeSession() {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException e) {
				System.err.println(">>> ERRO AO TENTAR FECHAR A CONEXAO! <<<");
			}
		}
	}
}