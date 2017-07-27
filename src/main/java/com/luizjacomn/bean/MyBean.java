package com.luizjacomn.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.luizjacomn.bo.EscolaBO;
import com.luizjacomn.bo.MunicipioBO;
import com.luizjacomn.entity.Escola;
import com.luizjacomn.entity.Municipio;

@ManagedBean
@ViewScoped
public class MyBean implements Serializable {
	private static final long serialVersionUID = 87376511160520638L;

	private Municipio municipio;

	private Escola origem;
	private Escola destino;

	private Integer indexOrigem;
	private Integer indexDestino;

	public MyBean() {
		iniciar();
	}

	@PostConstruct
	public void init() {
		municipio = MunicipioBO.getInstance().getMunicipio(1L);
		setIndexOrigem(0);
		setIndexDestino(1);
		buscar();
	}

	public void buscar() {
		origem = EscolaBO.getInstance().getEscolaPorMunicipio(municipio).get(getIndexOrigem());
		destino = EscolaBO.getInstance().getEscolaPorMunicipio(municipio).get(getIndexDestino());
	}

	public void iniciar() {
		executeJS("initialize(-5.10562, -38.3671)");
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Escola getOrigem() {
		return origem;
	}

	public void setOrigem(Escola origem) {
		this.origem = origem;
	}

	public Escola getDestino() {
		return destino;
	}

	public void setDestino(Escola destino) {
		this.destino = destino;
	}

	public Integer getIndexOrigem() {
		return indexOrigem;
	}

	public void setIndexOrigem(Integer indexOrigem) {
		this.indexOrigem = indexOrigem;
	}

	public Integer getIndexDestino() {
		return indexDestino;
	}

	public void setIndexDestino(Integer indexDestino) {
		this.indexDestino = indexDestino;
	}

	public static void executeJS(String javascript) {
		RequestContext.getCurrentInstance().execute(javascript);
	}

	// public String getParameterValue(String parametro) {
	// return getExternalContext().getRequestParameterMap().get(parametro);
	// }
	//
	// public boolean hasParameters() {
	// return !getExternalContext().getRequestParameterMap().isEmpty();
	// }
	//
	// private ExternalContext getExternalContext() {
	// return FacesContext.getCurrentInstance().getExternalContext();
	// }
}