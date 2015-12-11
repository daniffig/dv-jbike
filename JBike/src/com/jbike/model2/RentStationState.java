package com.jbike.model2;

public enum RentStationState {

	IN_OPERATION("In operation", "http://maps.google.com/mapfiles/ms/micons/green-dot.png"), UNDER_CONSTRUCTION(
			"Under construction", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"), OFFLINE("Offline",
							"http://maps.google.com/mapfiles/ms/micons/red-dot.png");

	private String name;
	private String icon;

	RentStationState(String name, String icon) {
		this.setName(name);
		this.setIcon(icon);
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public boolean canReceiveRequests() {
		return true;
	}

}
