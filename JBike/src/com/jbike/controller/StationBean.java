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

	@PostConstruct
	public void init() {
		stations = new ArrayList<Station>();
		
		for (int i = 1; i <= 20; i++) {
			stations.add(new Station(i, "Station " + i));
		}
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
}
