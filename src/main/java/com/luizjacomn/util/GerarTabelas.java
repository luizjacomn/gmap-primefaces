package com.luizjacomn.util;

public class GerarTabelas {
	public static void main(String[] args) {
		HibernateUtil.getSession();
		
		HibernateUtil.closeSession();
	}
}