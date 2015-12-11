package com.jbike.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bike implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private BikeState state;
	private Station currentStation;

	public static final Map<Long, Bike> bikes;

	static {
		bikes = new HashMap<Long, Bike>();

		Random random = new Random();

		for (Long i = 1L; i <= 100; i++) {
			bikes.put(i, new Bike(i, "Bike " + i, BikeState.values()[random.nextInt(BikeState.values().length)],
					Station.stations.get(random.nextInt(Station.stations.size()) + 1)));
		}
	}

	public Bike() {

	}

	public Bike(Long id, String name, BikeState state, Station currentStation) {
		this.id = id;
		this.name = name;
		this.setState(state);
		this.currentStation = currentStation;
	}

	public String toString() {
		return this.getName();
	}

	public boolean equals(Bike bike) {
		return this.getId() == bike.getId();
	}

	public boolean save() {
		if (this.isNew()) {
			this.setId((long) Bike.bikes.size() + 1);
			
			Bike.bikes.put(this.getId(), this);
		}

		return true;
	}

	public boolean isNew() {
		return this.getId() == null;
	}

	// TODO
	public boolean canBeRequested() {
		return this.state.canBeRequested();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
