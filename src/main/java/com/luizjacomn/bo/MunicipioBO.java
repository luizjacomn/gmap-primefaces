package com.luizjacomn.bo;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.luizjacomn.entity.Municipio;
import com.luizjacomn.util.JPAUtil;

public class MunicipioBO {

	private MunicipioBO() {
	}

	public static MunicipioBO getInstance() {
		return new MunicipioBO();
	}

	public Municipio getMunicipio(Long id) {
		EntityManager em = JPAUtil.getEM();
		Session session = null;
		try {
			session = em.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Municipio.class);
			criteria.add(Restrictions.eq("id", id));
			return (Municipio) criteria.uniqueResult();
		} finally {
			session.close();
//			em.close();
		}
	}
}