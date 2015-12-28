package com.jbike.model;

public enum MovementState {

	NEW("New"), CONFIRMED("Confirmed"), CANCELLED("Cancelled"), FINISHED("Finished");

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

	public boolean canBeConfirmed() {
		return this.equals(MovementState.NEW);
	}

	public boolean canBeCancelled() {
		return this.equals(MovementState.NEW);
	}

	public boolean canBeFinished() {
		return this.equals(MovementState.CONFIRMED);
	}

	public boolean canBeEdited() {
		return this.equals(MovementState.NEW);
	}
}
