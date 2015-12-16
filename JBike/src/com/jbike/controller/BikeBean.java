package com.jbike.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.jbike.model2.Bike;
import com.jbike.model2.BikeState;
import com.jbike.persistence.BikeDaoHibernate;
import com.jbike.persistence.FactoryDao;

@ManagedBean(name = "bikeBean")
@ApplicationScoped
public class BikeBean {

	private BikeDaoHibernate bikeDAO;

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

	public List<SelectItem> getStateOptions() {
		List<SelectItem> options = new ArrayList<SelectItem>();

		options.add(new SelectItem("", "Select One"));

		for (BikeState state : BikeState.values()) {
			options.add(new SelectItem(state));
		}

		return options;
	}

	public BikeDaoHibernate getBikeDAO() {
		return bikeDAO;
	}

	public void setBikeDAO(BikeDaoHibernate bikeDAO) {
		this.bikeDAO = bikeDAO;
	}
}
