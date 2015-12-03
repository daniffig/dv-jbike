package com.jbike.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Station implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private String coordinates;
	private int bikeQty;
	private int parkingSpaces;
	private int availableParkingSpaces;
	private int stateId;
	
	public static final Map<Integer, Station> stations;
	
	static {
		stations = new HashMap<Integer, Station>();
		
		stations.put(1, new Station(1, "Plaza Moreno", "Calle 12 964, La Plata, Buenos Aires", "-34.920314, -57.953833", 1));
		stations.put(2, new Station(2, "Plaza San Martín", "Avenida 7 902, La Plata, Buenos Aires", "-34.914732, -57.949658", 1));
		stations.put(3, new Station(3, "Plaza Italia", "Plaza Italia 129, La Plata, Buenos Aires", "-34.911381, -57.954861", 2));
		stations.put(4, new Station(4, "Estación de trenes", "Avenida 1 558, La Plata, Buenos Aires", "-34.904771, -57.949443", 1));
		stations.put(5, new Station(5, "Terminal de ómnibus", "Terminal de Ómnibus de La Plata, La Plata, Buenos Aires", "-34.905416, -57.954571", 1));
	}
	
	public Station(int id, String name, String address, String coordinates, int stateId) {
		this.id = id;
		this.name = name;
		
		this.address = address;
		this.coordinates = coordinates;
		this.bikeQty = id;
		this.parkingSpaces = id;
		this.availableParkingSpaces = 0;
		this.stateId = stateId;
	}
	
	public String toString() {
		return this.getName();
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
	public int getBikeQty() {
		return bikeQty;
	}
	public void setBikeQty(int bikeQty) {
		this.bikeQty = bikeQty;
	}	
	public int getParkingSpaces() {
		return parkingSpaces;
	}
	public void setParkingSpaces(int parkingSpaces) {
		this.parkingSpaces = parkingSpaces;
	}
	public int getAvailableParkingSpaces() {
		return availableParkingSpaces;
	}
	public void setAvailableParkingSpaces(int availableParkingSpaces) {
		this.availableParkingSpaces = availableParkingSpaces;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}	
}
