package com.jbike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.jbike.model.Bike;
import com.jbike.model.BikeState;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.interfaces.BikeDao;

@ManagedBean(name = "bikeBean")
@ApplicationScoped
public class BikeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8891899362486959859L;
	private BikeDao bikeDAO;

	@PostConstruct
	public void init() {
		this.setBikeDAO(FactoryDao.getBikeDao());
	}

	public boolean saveBike(Bike bike) {
		if (bike.isNew()) {
			return this.getBikeDAO().save(bike);
		} else {
			return this.getBikeDAO().update(bike);
		}
	}

	public List<Bike> getBikes() {
		return this.getBikeDAO().findAll();
	}

	public List<BikeState> getStates() {
		return Arrays.asList(BikeState.values());
	}

	public List<SelectItem> getStateOptions() {
		List<SelectItem> options = new ArrayList<SelectItem>();

		for (BikeState state : BikeState.values()) {
			options.add(new SelectItem(state));
		}

		return options;
	}

	public BikeDao getBikeDAO() {
		return bikeDAO;
	}

	public void setBikeDAO(BikeDao bikeDAO) {
		this.bikeDAO = bikeDAO;
	}
}
