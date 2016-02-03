package com.jbike.view;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.jbike.controller.StationBean;
import com.jbike.helper.StationHelper;
import com.jbike.model.Station;
import com.jbike.session.UserSession;

@ManagedBean(name = "stationView")
@ViewScoped
public class StationView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5027241248158849238L;
	private List<Station> stations;
	private List<Station> filteredStations;

	@ManagedProperty(value = "#{userSession.selectedStation}")
	private Station station;

	private MapModel advancedModel;
	private MapModel emptyModel;
	private Marker marker;

	@ManagedProperty("#{stationBean}")
	private StationBean stationBean;

	@ManagedProperty("#{userSession}")
	private UserSession userSession;

	private PieChartModel sourcePieModel;
	private PieChartModel destinationPieModel;

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (this.getUserSession().getMessageQueue().size() > 0) {

		}

		emptyModel = new DefaultMapModel();

		stations = stationBean.getStations();

		advancedModel = new DefaultMapModel();

		for (Station station : stations) {
			Marker marker = StationHelper.toMarker(station);

			if (marker != null) {
				advancedModel.addOverlay(StationHelper.toMarker(station));
			}
		}
		
		sourcePieModel = new PieChartModel();
		sourcePieModel.setData(this.getStationsForSourceChart());
		sourcePieModel.setTitle("Usage as source");        
		sourcePieModel.setShowDataLabels(true);
		sourcePieModel.setLegendPosition("w");
		
		destinationPieModel = new PieChartModel();
		destinationPieModel.setData(this.getStationsForDestinationChart());
		destinationPieModel.setTitle("Usage as destination");        
		destinationPieModel.setShowDataLabels(true);
		destinationPieModel.setLegendPosition("w");
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public String getFormTitle() {
		return this.getStation().isNew() ? "New Station" : String.format("Edit Station (%s)", this.getStation());
	}

	public String viewBikes(Station station) {
		this.getUserSession().setSelectedStation(station);

		return "bikes/list";
	}

	public String viewInformation(Station station) {
		this.getUserSession().setSelectedStation(station);

		return "stations/information";
	}

	public String viewForm(Station station) {
		if (station == null) {
			station = new Station();
		}

		this.getUserSession().setSelectedStation(station);

		return "stations/form";
	}

	public String delete(Station station) {
		if (this.getStationBean().deleteStation(station)) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Station successfully deleted."));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while deleting the station."));
		}

		return "stations/list";
	}

	// TODO Validaciones!
	public String save() {
		String message = this.getStation().isNew() ? "Station successfully created." : "Station successfully updated.";

		if (this.getStationBean().saveStation(this.getStation())) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));

			return "stations/list";
		}
		
		this.getUserSession().getMessageQueue().offer(
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "An error occurred while saving the station."));

		return "stations/form";
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
	
	public PieChartModel getSourcePieModel() {
		return sourcePieModel;
	}

	public void setSourcePieModel(PieChartModel pieModel) {
		this.sourcePieModel = pieModel;
	}
	
	public PieChartModel getDestinationPieModel() {
		return destinationPieModel;
	}

	public void setDestinationPieModel(PieChartModel pieModel) {
		this.destinationPieModel = pieModel;
	}

	@SuppressWarnings("rawtypes")
	public Map getStationsForSourceChart() {
		Map<String, Integer> stations = new LinkedHashMap<String, Integer>();
		
		for (Station station : stationBean.getStations()) {
			stations.put(station.getName(), station.getMovementsAsSource().size());
		}
		
		return stations;		
	}

	@SuppressWarnings("rawtypes")
	public Map getStationsForDestinationChart() {
		Map<String, Integer> stations = new LinkedHashMap<String, Integer>();
		
		for (Station station : stationBean.getStations()) {
			stations.put(station.getName(), station.getMovementsAsDestination().size());
		}
		
		return stations;		
	}
}
