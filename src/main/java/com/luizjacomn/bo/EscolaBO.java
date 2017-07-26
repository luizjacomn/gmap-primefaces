package com.luizjacomn.bo;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.map.Marker;

import com.luizjacomn.entity.Escola;
import com.luizjacomn.entity.Municipio;
import com.luizjacomn.util.JPAUtil;

public class EscolaBO {

	private EscolaBO() {
	}

	public static EscolaBO getInstance() {
		return new EscolaBO();
	}

	public void salvar(Escola escola) {
		EntityManager em = JPAUtil.getEM();
		try {
			em.getTransaction().begin();
			em.merge(escola);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Escola> getEscolaPorMunicipio(Municipio municipio) {
		EntityManager em = JPAUtil.getEM();
		Session session = null;
		try {
			session = em.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Escola.class);
			criteria.add(Restrictions.eq("municipio", municipio));
			return criteria.list();
		} finally {
			session.close();
			// em.close();
		}
	}
	
	public Escola getEscolaPorCoordenadas(Marker local) {
		EntityManager em = JPAUtil.getEM();
		Session session = null;
		try {
			session = em.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Escola.class);
			criteria.add(Restrictions.eq("latitude", local.getLatlng().getLat()));
			criteria.add(Restrictions.eq("longitude", local.getLatlng().getLng()));
			Escola escola = (Escola) criteria.uniqueResult(); 
			return escola;
		} finally {
			session.close();
//			em.close();
		}
	}
}