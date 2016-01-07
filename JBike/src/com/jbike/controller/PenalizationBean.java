package com.jbike.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.jbike.model.Penalization;
import com.jbike.model.User;
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

	public boolean deletePenalization(Penalization penalization) {
		if (penalization.canBeDeleted()) {
			return this.getPenalizationDAO().delete(penalization);
		} else {
			return false;
		}
	}

	public List<Penalization> getPenalizations() {
		return this.getPenalizationDAO().findAll();
	}

	public List<Penalization> getPenalizations(User user) {
		return this.getPenalizationDAO().findAll(user);
	}

	public PenalizationDao getPenalizationDAO() {
		return penalizationDAO;
	}

	public void setPenalizationDAO(PenalizationDao penalizationDAO) {
		this.penalizationDAO = penalizationDAO;
	}
	
	public List<Penalization> getActivePenalizations(User user) {
		return this.getPenalizationDAO().findAllCurrentlyActive(user);
	}
}
