package com.luizjacomn.entity;

import static org.junit.Assert.assertEquals;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.luizjacomn.util.JPAUtil;

public class TesteMunicipioDAO {

	private Session session = null;

	@Test
	public void test() {
		String nome = getMunicipio(1L).getNome();
		assertEquals("Morada Nova", nome);
	}

	public Municipio getMunicipio(Long id) {
		try {
			// session = HibernateUtil.getSession();
			session = JPAUtil.getEM().unwrap(Session.class);
			Criteria criteria = session.createCriteria(Municipio.class);
			criteria.add(Restrictions.eq("id", id));
			return (Municipio) criteria.uniqueResult();
		} finally {
			session.close();
		}
	}
}