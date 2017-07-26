package com.luizjacomn.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.luizjacomn.bo.EscolaBO;
import com.luizjacomn.bo.MunicipioBO;
import com.luizjacomn.entity.Municipio;

@ManagedBean
@ViewScoped
public class MyBean implements Serializable {
	private static final long serialVersionUID = 87376511160520638L;

	private Municipio municipio;
	private String origem;
	private String destino;
	
	public MyBean() {
		iniciar();
	}
	
	@PostConstruct
	public void init() {
		municipio = new Municipio();
	}

	public void buscar() {
		municipio = MunicipioBO.getInstance().getMunicipio(1L);
		origem = EscolaBO.getInstance().getEscolaPorMunicipio(municipio).get(0).getCoordenadasCentro();
		municipio = MunicipioBO.getInstance().getMunicipio(2L);
		destino = EscolaBO.getInstance().getEscolaPorMunicipio(municipio).get(0).getCoordenadasCentro();
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
	
	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
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