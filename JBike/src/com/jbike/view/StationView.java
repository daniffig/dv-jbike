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

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.jbike.controller.StationBean;
import com.jbike.helper.StationHelper;
import com.jbike.model2.Station;
import com.jbike.model2.StationState;
import com.jbike.session.UserSession;

@ManagedBean(name = "stationView")
@ViewScoped
public class StationView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Station> stations;
	private List<Station> filteredStations;

	@ManagedProperty(value = "#{userSession.selectedStation}")
	private Station station;

	private MapModel advancedModel;

	private Marker marker;

	@ManagedProperty("#{stationBean}")
	private StationBean stationBean;

	@ManagedProperty("#{userSession}")
	private UserSession userSession;

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	@PostConstruct
	public void init() {
		emptyModel = new DefaultMapModel();
		
		stations = stationBean.getStations();

		advancedModel = new DefaultMapModel();

		for (Station station : stations) {
			Marker marker = StationHelper.toMarker(station);
			
			if (marker != null) {
				advancedModel.addOverlay(StationHelper.toMarker(station));
			}
		}
	}

	public String viewForm(Station station) {
		if (station == null) {
			station = new Station();
		}

		this.getUserSession().setSelectedStation(station);

		return "/admin/stations/form.xhtml?faces-redirect=true";
	}

	// TODO Validaciones!
	public String save() {
		String message = this.getStation().isNew() ? "Station successfully created." : "Station successfully updated.";
		
		/*
		this.getStation().setLatitude(-34.920314);
		this.getStation().setLongitude(-57.95383);
		*/
		this.getStation().setState(StationState.IN_OPERATION);

		if (this.getStationBean().saveStation(this.getStation())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while saving the station."));
		}

		return "/admin/stations/list.xhtml?faces-redirect=true";
	}

	public String backToList() {
		return "/admin/stations/list.xhtml?faces-redirect=true";
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

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

	private Double lat;
	private Double lng;
	private MapModel emptyModel;
}
