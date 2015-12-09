package com.jbike.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bike {

	private int id;
	private String name;
	private BikeState state;
	private Station currentStation;

	public static final Map<Integer, Bike> bikes;

	static {
		bikes = new HashMap<Integer, Bike>();

		Random random = new Random();

		for (int i = 1; i <= 100; i++) {
			bikes.put(i, new Bike(i, "Bike " + i, BikeState.values()[random.nextInt(BikeState.values().length)],
					Station.stations.get(random.nextInt(Station.stations.size()) + 1)));
		}
	}

	public Bike(int id, String name, BikeState state, Station currentStation) {
		this.id = id;
		this.name = name;
		this.setState(state);
		this.currentStation = currentStation;
	}

	public String toString() {
		return this.getName();
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

	public BikeState getState() {
		return state;
	}

	public void setState(BikeState state) {
		this.state = state;
	}

	public Station getCurrentStation() {
		return this.currentStation;
	}

	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}

}
