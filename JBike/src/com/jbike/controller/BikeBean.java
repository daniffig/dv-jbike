package com.jbike.controller;

import com.jbike.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "bikeBean")
@ApplicationScoped
public class BikeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Bike> bikes;

	private int id;
	private String name;

	@PostConstruct
	public void init() {
		bikes = new ArrayList<Bike>();

		Random random = new Random();

		for (int i = 1; i <= 100; i++) {
			bikes.add(new Bike(i, "Bike " + i, BikeState.values()[random.nextInt(BikeState.values().length)],
					random.nextInt(5) + 1));
		}
	}

	public String addBike() {
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
