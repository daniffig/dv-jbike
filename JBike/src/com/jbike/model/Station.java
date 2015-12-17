package com.jbike.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Station {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;
	
	private String address;

	@Enumerated(EnumType.ORDINAL)
	private StationState state;

	private Double latitude;

	private Double longitude;

	@Column(name = "total_parking_spaces")
	private Integer totalParkingSpaces;

	@OneToMany(mappedBy = "currentStation")
	private List<Bike> bikes;

	public Station() {
	}

	public Station(String name) {
		this.name = name;
	}

	public Station(String name, Integer totalParkingSpaces) {
		this.name = name;
		this.totalParkingSpaces = totalParkingSpaces;
		this.state = StationState.IN_OPERATION;
	}

	public Station(String name, Integer totalParkingSpaces, Double latitude, Double longitude) {
		this.name = name;
		this.totalParkingSpaces = totalParkingSpaces;
		this.latitude = latitude;
		this.longitude = longitude;
		this.state = StationState.IN_OPERATION;
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
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

	public StationState getState() {
		return this.state;
	}

	public void setState(StationState state) {
		this.state = state;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getTotalParkingSpaces() {
		return totalParkingSpaces;
	}

	public void setTotalParkingSpaces(Integer totalParkingSpaces) {
		this.totalParkingSpaces = totalParkingSpaces;
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}
	
	public boolean isNew(){
		return (this.id==null);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return this.getName();
	}
	
	public Integer getAvailableParkingSpaces(){
		return this.totalParkingSpaces - this.bikes.size();
	}
}
