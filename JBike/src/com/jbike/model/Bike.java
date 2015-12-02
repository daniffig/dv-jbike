package com.jbike.model;

public class Bike {

	private int id;
	private String name;
	private int stateId;
	private int statusId;
	private int currentStationId;

	public Bike(int id, String name, int stateId, int statusId, int currentStationId) {
		this.id = id;
		this.name = name;
		this.stateId = stateId;
		this.statusId = statusId;
		this.currentStationId = currentStationId;
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

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCurrentStationId() {
		return currentStationId;
	}

	public void setCurrentStationId(int currentStationId) {
		this.currentStationId = currentStationId;
	}

}
