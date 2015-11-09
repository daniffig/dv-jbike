package com.jbike.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.jbike.controller.StationBean;
import com.jbike.model.Station;

@ManagedBean(name = "stationView")
@ViewScoped
public class StationView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Station> stations;

	@ManagedProperty("#{stationBean}")
	private StationBean stationBean;

	@PostConstruct
	public void init() {
		stations = stationBean.getStations();
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Station edited " + ((Station) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit cancelled " + ((Station) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public StationBean getStationBean() {
		return stationBean;
	}

	public void setStationBean(StationBean stationBean) {
		this.stationBean = stationBean;
	}
}
