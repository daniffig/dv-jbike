package com.jbike.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.jbike.model.Station;

@ManagedBean(name = "stationBean")
@ApplicationScoped
public class StationBean {

	private List<Station> stations;
	private Station station;

	private String name;

	@PostConstruct
	public void init() {
		stations = new ArrayList<Station>();

		for (int i = 1; i <= 20; i++) {
			stations.add(new Station(i, "Station " + i));
		}
	}

	public String addStation() {
		Station station = new Station(10, name);
		stations.add(station);
		return null;
	}

	public String editStation(Station station) {
		return null;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
