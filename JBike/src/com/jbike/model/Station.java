package com.jbike.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Station implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private String coordinates;
	private int parkingSpaces;
	private StationState state;

	public static final Map<Integer, Station> stations;

	static {
		stations = new HashMap<Integer, Station>();

		Random random = new Random();

		stations.put(1, new Station(1, "Plaza Moreno", "Calle 12 964, La Plata, Buenos Aires", "-34.920314, -57.953833",
				random.nextInt(100) + 100, StationState.values()[random.nextInt(StationState.values().length)]));
		stations.put(2,
				new Station(2, "Plaza San Martín", "Avenida 7 902, La Plata, Buenos Aires", "-34.914732, -57.949658",
						random.nextInt(100) + 100,
						StationState.values()[random.nextInt(StationState.values().length)]));
		stations.put(3,
				new Station(3, "Plaza Italia", "Plaza Italia 129, La Plata, Buenos Aires", "-34.911381, -57.954861",
						random.nextInt(100) + 100,
						StationState.values()[random.nextInt(StationState.values().length)]));
		stations.put(4,
				new Station(4, "Estación de trenes", "Avenida 1 558, La Plata, Buenos Aires", "-34.904771, -57.949443",
						random.nextInt(100) + 100,
						StationState.values()[random.nextInt(StationState.values().length)]));
		stations.put(5,
				new Station(5, "Terminal de ómnibus", "Terminal de Ómnibus de La Plata, La Plata, Buenos Aires",
						"-34.905416, -57.954571", random.nextInt(100) + 100,
						StationState.values()[random.nextInt(StationState.values().length)]));
	}

	public Station(int id, String name, String address, String coordinates, int parkingSpaces, StationState state) {
		this.id = id;
		this.name = name;

		this.address = address;
		this.coordinates = coordinates;
		this.parkingSpaces = parkingSpaces;
		this.setState(state);
	}

	public String toString() {
		return String.format("%s (%s)", this.getName(), this.getState().toString());
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public int getParkingSpaces() {
		return parkingSpaces;
	}

	public void setParkingSpaces(int parkingSpaces) {
		this.parkingSpaces = parkingSpaces;
	}

	public StationState getState() {
		return state;
	}

	public void setState(StationState state) {
		this.state = state;
	}

	public boolean canReceiveRequests() {
		return this.state.canReceiveRequests() && (this.getAvailableBikes() > 0);
	}

	// TODO
	public int getAvailableBikes() {
		return 10;
	}

	public int getAvailableParkingSpaces() {
		return this.getParkingSpaces() - this.getAvailableBikes();
	}

}
