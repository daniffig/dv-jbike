package com.jbike.view;

import com.jbike.controller.BikeBean;
import com.jbike.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "bikeView")
@ViewScoped
public class BikeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Bike> filteredBikes;

	private Bike bike;

	private int currentStationId;

	@ManagedProperty("#{bikeBean}")
	private BikeBean bikeBean;

	@PostConstruct
	public void init() {
		//bikes = bikeBean.getBikes();
		bike = new Bike();
	}

	public String create() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("bike", new Bike());

		return "admin/bikes/form";
	}

	public String update(Bike bike) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("bike", bike);

		return "admin/bikes/form";
	}

	public void edit(Bike bike) {
		this.setBike(bike);
	}

	public void save() {
		this.getBike().save();
	}

	public void resetForm() {
		bike = new Bike();
	}

	public List<Bike> getBikes() {
		return new ArrayList<Bike>(Bike.bikes.values());
		//return bikes;
	}
/*
	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}
*/	

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

	public List<SelectItem> getStateOptions() {
		List<SelectItem> options = new ArrayList<SelectItem>();

		options.add(new SelectItem("", "Select One"));

		for (BikeState state : BikeState.values()) {
			options.add(new SelectItem(state));
		}

		return options;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}
}
