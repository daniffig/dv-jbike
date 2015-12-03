package com.jbike.view;

import com.jbike.controller.BikeBean;
import com.jbike.model.*;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "bikeView")
@ViewScoped
public class BikeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Bike> bikes;
	private List<Bike> filteredBikes;
	
	private int currentStationId;

	@ManagedProperty("#{bikeBean}")
	private BikeBean bikeBean;

	@PostConstruct
	public void init() {
		bikes = bikeBean.getBikes();
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public BikeBean getBikeBean() {
		return bikeBean;
	}

	public void setBikeBean(BikeBean bikeBean) {
		this.bikeBean = bikeBean;
	}

	public List<Bike> getFilteredBikes() {
		return filteredBikes;
	}

	public void setFilteredBikes(List<Bike> filteredBikes) {
		this.filteredBikes = filteredBikes;
	}

	public int getCurrentStationId() {
		return currentStationId;
	}

	public void setCurrentStationId(int currentStationId) {
		this.currentStationId = currentStationId;
	}
}
