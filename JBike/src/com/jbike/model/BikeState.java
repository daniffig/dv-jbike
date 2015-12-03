package com.jbike.model;

public enum BikeState {

	EXCELLENT, GOOD, AVERAGE, BELOW_AVERAGE, POOR;

	public String toString() {
		return this.name().charAt(0) + this.name().substring(1).toLowerCase();
	}
}
