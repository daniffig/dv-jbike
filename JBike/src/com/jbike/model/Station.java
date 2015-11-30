package com.jbike.model;

import java.io.Serializable;

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
	
	public Station(int id, String name) {
		this.id = id;
		this.name = name;
		
		this.address = "a String";
		this.coordinates = "a String";
		this.bikeQty = id;
		this.parkingSpaces = id;
		this.availableParkingSpaces = 0;
		this.stateId = 1;
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
