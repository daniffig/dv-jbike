package com.jbike.model;

public class Bike {

	private int id;
	private String name;
	private int stateId;
	private BikeState status;
	private int currentStationId;

	public Bike(int id, String name, int stateId, BikeState status, int currentStationId) {
		this.id = id;
		this.name = name;
		this.stateId = stateId;
		this.setStatus(status);
		this.currentStationId = currentStationId;
	}
	
	// TODO
	public boolean canBeRequested() {
		return true;
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

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public BikeState getStatus() {
		return status;
	}

	public void setStatus(BikeState status) {
		this.status = status;
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

}
