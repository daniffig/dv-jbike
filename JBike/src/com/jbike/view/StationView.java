package com.jbike.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.jbike.controller.StationBean;
import com.jbike.helper.StationHelper;
import com.jbike.model.Station;
import com.jbike.model.StationState;

@ManagedBean(name = "stationView")
@ViewScoped
public class StationView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Station> stations;
	private List<Station> filteredStations;
	private Station selectedStation;

	private MapModel advancedModel;

	private Marker marker;

	@ManagedProperty("#{stationBean}")
	private StationBean stationBean;

	@PostConstruct
	public void init() {
		stations = stationBean.getStations();

		advancedModel = new DefaultMapModel();

		// TODO Esto deberíamos hacerlo en un helper, algún toMarker para
		// Station.
		for (Station station : stations) {
			advancedModel.addOverlay(StationHelper.toMarker(station));
		}

		/*
		 * LatLng plazaMoreno = new LatLng(-34.921380, -57.952869); LatLng
		 * plazaSanMartin = new LatLng(-34.914685, -57.949361); LatLng
		 * plazaItalia = new LatLng(-34.911204, -57.955010); LatLng
		 * estacionTrenes = new LatLng(-34.904675, -57.949345); LatLng
		 * terminalOmnibus = new LatLng(-34.905652, -57.954259);
		 * 
		 * advancedModel.addOverlay(new Marker(plazaMoreno, "Plaza Moreno",
		 * stations.get(0))); advancedModel.addOverlay(new
		 * Marker(plazaSanMartin, "Plaza San Martín"));
		 * advancedModel.addOverlay(new Marker(plazaItalia, "Plaza Italia"));
		 * advancedModel.addOverlay(new Marker(estacionTrenes,
		 * "Estación de Trenes")); advancedModel.addOverlay(new
		 * Marker(terminalOmnibus, "Terminal de Ómnibus"));
		 */
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Station edited " + ((Station) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit cancelled " + ((Station) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		this.setMarker((Marker) event.getOverlay());
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

	public List<Station> getFilteredStations() {
		return filteredStations;
	}

	public void setFilteredStations(List<Station> filteredStations) {
		this.filteredStations = filteredStations;
	}

	public Station getSelectedStation() {
		return selectedStation;
	}

	public void setSelectedStation(Station selectedStation) {
		this.selectedStation = selectedStation;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	public List<SelectItem> getStationOptions() {
		List<SelectItem> options = new ArrayList<SelectItem>();

		options.add(new SelectItem("", "Select One"));

		for (Station station : stations) {
			options.add(new SelectItem(station.getId(), station.getName()));
		}

		return options;
	}

	public List<SelectItem> getStateOptions() {
		List<SelectItem> options = new ArrayList<SelectItem>();

		options.add(new SelectItem("", "Select One"));

		for (StationState state : StationState.values()) {
			options.add(new SelectItem(state));
		}

		return options;
	}
}
