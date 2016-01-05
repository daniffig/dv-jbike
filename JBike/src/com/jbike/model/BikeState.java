package com.jbike.model;

public enum BikeState {

	AVAILABLE("Available"), REQUESTED("Requested"), IN_USE("In use"), REPORTED("Reported"), UNDER_MAINTENANCE(
			"Under maintenance");

	private String name;

	BikeState(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean canBeRequested() {
		return this.equals(BikeState.AVAILABLE);
	}

	public boolean canBeReported() {
		return this.equals(BikeState.AVAILABLE);
	}
}