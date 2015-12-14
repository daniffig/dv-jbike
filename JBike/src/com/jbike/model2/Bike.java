package com.jbike.model2;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bike")
public class Bike {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "created_at")
	private Date createdAt;

	private String brand;

	private String model;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Station station;

	@Enumerated(EnumType.ORDINAL)
	private BikeState state;

	public Bike() {
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Date((new java.util.Date()).getTime());
	}

	public Bike(String brand, String model) {
		this.brand = brand;
		this.model = model;
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Date((new java.util.Date()).getTime());
	}

	public Bike(String brand, String model, Station station) {
		this.brand = brand;
		this.model = model;
		this.station = station;
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Date((new java.util.Date()).getTime());
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public BikeState getState() {
		return state;
	}

	public void setState(BikeState state) {
		this.state = state;
	}
}
