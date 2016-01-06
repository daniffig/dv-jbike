package com.jbike.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.jbike.model.Penalization;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.interfaces.PenalizationDao;

@ManagedBean(name = "penalizationBean")
@ApplicationScoped
public class PenalizationBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 500680392459421403L;
	private PenalizationDao penalizationDAO;

	@PostConstruct
	public void init() {
		this.setPenalizationDAO(FactoryDao.getPenalizationDao());
	}

	public boolean savePenalization(Penalization penalization) {
		if (penalization.isNew()) {
			return this.getPenalizationDAO().save(penalization);
		} else {
			return this.getPenalizationDAO().update(penalization);
		}
	}

	public List<Penalization> getPenalizations() {
		return this.getPenalizationDAO().findAll();
	}

	public PenalizationDao getPenalizationDAO() {
		return penalizationDAO;
	}

	public void setPenalizationDAO(PenalizationDao penalizationDAO) {
		this.penalizationDAO = penalizationDAO;
	}
}
