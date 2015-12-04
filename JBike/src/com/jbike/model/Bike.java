package com.jbike.model;

public class Bike {

	private int id;
	private String name;
	private BikeState state;
	private int currentStationId;

	public Bike(int id, String name, BikeState state, int currentStationId) {
		this.id = id;
		this.name = name;
		this.setState(state);
		this.currentStationId = currentStationId;
	}

	// TODO
	public boolean canBeRequested() {
		return this.state.canBeRequested();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentStationId() {
		return currentStationId;
	}

	public void setCurrentStationId(int currentStationId) {
		this.currentStationId = currentStationId;
	}

	public Station getCurrentStation() {
		return Station.stations.get(this.currentStationId);
	}

	public BikeState getState() {
		return state;
	}

	public void setState(BikeState state) {
		this.state = state;
	}

}
