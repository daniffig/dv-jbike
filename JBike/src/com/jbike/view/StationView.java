package com.jbike.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.jbike.controller.StationBean;
import com.jbike.helper.StationHelper;
import com.jbike.model.Station;
import com.jbike.session.UserSession;

@ManagedBean(name = "stationView")
@RequestScoped
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

	public String getFormTitle() {
		return this.getStation().isNew() ? "New Station" : String.format("Edit Station (%s)", this.getStation());
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

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

	private MapModel emptyModel;
}
