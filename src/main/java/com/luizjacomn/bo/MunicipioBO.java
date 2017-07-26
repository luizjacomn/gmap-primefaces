package com.luizjacomn.bo;

import javax.persistence.EntityManager;

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
		try {
			return em.find(Municipio.class, id);
		} finally {
			em.close();
		}
	}
}