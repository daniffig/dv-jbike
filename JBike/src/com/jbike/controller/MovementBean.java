package com.jbike.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.jbike.model.Movement;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.interfaces.MovementDao;

@ManagedBean(name = "movementBean")
@ApplicationScoped
public class MovementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7421057914797478637L;
	private MovementDao movementDAO;

	@PostConstruct
	public void init() {
		this.setMovementDAO(FactoryDao.getMovementDao());
	}

	public boolean saveMovement(Movement movement) {
		if (movement.isNew()) {
			return this.getMovementDAO().save(movement);
		} else {
			return this.getMovementDAO().update(movement);
		}
	}

	public List<Movement> getMovements() {
		return this.getMovementDAO().findAll();
	}

	public MovementDao getMovementDAO() {
		return movementDAO;
	}

	public void setMovementDAO(MovementDao movementDAO) {
		this.movementDAO = movementDAO;
	}
}
