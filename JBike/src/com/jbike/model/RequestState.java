package com.jbike.model;

public enum RequestState {

	NEW("New"), APPROVED("Approved"), REJECTED("Rejected"), CANCELLED("Cancelled");
	
	private String name;
	
	RequestState(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
