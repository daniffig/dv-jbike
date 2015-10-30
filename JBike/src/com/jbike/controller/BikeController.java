package com.jbike.controller;

import com.jbike.model.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "bikeController")
@ApplicationScoped
public class BikeController {
	private List<Bike> bikes;

	private int id;
	private String name;

	@PostConstruct
	public void init() {
		bikes = new ArrayList<Bike>();
	}

	public String addBike() {
		bikes.add(new Bike(id, name));

		return null;
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
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
}
