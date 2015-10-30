package com.jbike.view;

import com.jbike.controller.BikeController;
import com.jbike.model.*;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="bikeView")
@ViewScoped
public class BikeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Bike> bikes;
	
	@ManagedProperty("#{bikeController}")
	private BikeController controller;
	
	@PostConstruct
	public void init() {
		bikes = controller.getBikes();
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public BikeController getController() {
		return controller;
	}

	public void setController(BikeController controller) {
		this.controller = controller;
	}	
}
