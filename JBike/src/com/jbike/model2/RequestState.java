package com.jbike.model2;

public enum RequestState {

	NEW("New"), APPROVED("Approved"), REJECTED("Rejected"), CANCELLED("Cancelled");

	private String name;

	RequestState(String name) {
		this.setName(name);
	}

	public boolean canBeCancelled() {
		return this.equals(RequestState.NEW);
	}

	public boolean canBeApproved() {
		return this.equals(RequestState.NEW);
	}

	public boolean canBeRejected() {
		return this.equals(RequestState.NEW);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
