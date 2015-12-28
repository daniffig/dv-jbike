package com.jbike.model;

public enum MovementState {
	
	NEW("New"), Confirmed("Confirmed"), CANCELLED("Cancelled"), FINISHED("Finished");

	private String name;

	MovementState(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
