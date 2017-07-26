package com.luizjacomn.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

import com.luizjacomn.bo.EscolaBO;
import com.luizjacomn.bo.MunicipioBO;
import com.luizjacomn.entity.Escola;
import com.luizjacomn.entity.Municipio;

@ManagedBean(name = "municipioBean")
@ViewScoped
public class MunicipioBean implements Serializable {

	private static final long serialVersionUID = -573473000570969434L;

	private MapModel escolasModel;

	// private Marker localSelecionado;

	private Municipio municipio;

	private Escola escola;

	private String center;

	private boolean isCriarRota;

	private List<LatLng> locaisRota;

	public MunicipioBean() {
		setMunicipio(MunicipioBO.getInstance().getMunicipio(1L));
		setCenter(municipio.getCoordenadasCentro());
		escolasModel = new DefaultMapModel();
		escola = new Escola();
		locaisRota = new ArrayList<>();
		buscarEscolas();
	}

	public void addMarker() {
		escola.setMunicipio(municipio);
		Marker local = new Marker(escola.getCoordenadas(), escola.getNome());
		escolasModel.addOverlay(local);

		EscolaBO.getInstance().salvar(escola);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Escola Adicionada!", escola.getNome()));
		setCenter(escola.getCoordenadasCentro());
		escola = new Escola();
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();

		if (isCriarRota) {
			Marker localSelecionado = (Marker) event.getOverlay();
			if (!locaisRota.contains(localSelecionado.getLatlng())) {
				locaisRota.add(localSelecionado.getLatlng());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Escola Marcada!", localSelecionado.getTitle()));
			}
		} else {
			Marker localSelecionado = (Marker) event.getOverlay();
			setEscola(EscolaBO.getInstance().getEscolaPorCoordenadas(localSelecionado));
			context.execute("PF('detalhe').show()");
		}
	}

	private void buscarEscolas() {
		List<Escola> escolas = EscolaBO.getInstance().getEscolaPorMunicipio(municipio);
		escolas.forEach(e -> escolasModel.addOverlay(new Marker(e.getCoordenadas(), e.getNome())));
	}

	public void teste() {
		setCriarRota(true);
	}

	public void tracarRota() {
		Polyline polyline = new Polyline();
		polyline.setPaths(locaisRota);

		polyline.setStrokeWeight(7);
		polyline.setStrokeColor("#FF9900");
		polyline.setStrokeOpacity(0.7);

		escolasModel.addOverlay(polyline);
		setCriarRota(false);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Rota traçada com sucesso!", null));
		
		locaisRota = new ArrayList<>();
	}

	public MapModel getEscolasModel() {
		return escolasModel;
	}

	// public Marker getLocalSelecionado() {
	// return localSelecionado;
	// }
	//
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public boolean isCriarRota() {
		return isCriarRota;
	}

	public void setCriarRota(boolean isCriarRota) {
		this.isCriarRota = isCriarRota;
	}

	public List<LatLng> getLocaisRota() {
		return locaisRota;
	}

	public void setLocaisRota(List<LatLng> locaisRota) {
		this.locaisRota = locaisRota;
	}
}