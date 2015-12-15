package com.jbike.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.jbike.controller.StationBean;
import com.jbike.helper.StationHelper;
import com.jbike.model.Station;
import com.jbike.model.StationState;
import com.jbike.navigation.NavigationBean;

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

	private Station station;

	private MapModel advancedModel;

	private Marker marker;

	@ManagedProperty("#{navigationBean}")
	private NavigationBean navigationBean;

	@ManagedProperty("#{stationBean}")
	private StationBean stationBean;

	@PostConstruct
	public void init() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		this.setStation((session.get("station") == null) ? new Station() : (Station) session.get("station"));

		session.remove("station");
		
		stations = stationBean.getStations();

		advancedModel = new DefaultMapModel();

		for (Station station : stations) {
			advancedModel.addOverlay(StationHelper.toMarker(station));
		}		
		
		Station station = (Station) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("station");
		
		if (station == null)
		{
			station = new Station();
		}
		
		this.setStation(station);
	}
	
	public String create() {
		return "admin/stations/form";
	}

	public String update(Station station) {		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("station", station);
		
		return "admin/stations/form";
	}
	
	// TODO Validaciones!
	public String save() {
		if (this.getStation().isNew()) {
			stationBean.getStations().add(this.getStation());
		}		
		
		return "admin/stations/list";
	}

	// FIXME Esto lo podríamos hacer global
	public Map<String, Object> getFormOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("closable", false);
		options.put("draggable", false);
		options.put("modal", true);
		options.put("resizable", false);

		return options;
	}

	public void openNewStationDialog() {
		RequestContext.getCurrentInstance().openDialog("/admin/stations/form", this.getFormOptions(), null);
	}

	public void openEditStationDialog(Station station) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("station", station);

		RequestContext.getCurrentInstance().openDialog("/admin/stations/form", this.getFormOptions(), null);
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

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}
