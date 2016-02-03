package com.jbike.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.jbike.model.BikeState;
import com.jbike.model.Movement;
import com.jbike.model.MovementState;
import com.jbike.model.User;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.interfaces.MovementDao;

@ManagedBean(name = "movementBean")
@ApplicationScoped
public class MovementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7421057914797478637L;

	@ManagedProperty("#{bikeBean}")
	private BikeBean bikeBean;
	private MovementDao movementDAO;
	@ManagedProperty("#{stationBean}")
	private StationBean stationBean;

	@PostConstruct
	public void init() {
		this.setMovementDAO(FactoryDao.getMovementDao());
	}

	public boolean saveMovement(Movement movement) {
		if (movement.isNew()) {
			movement.getBike().setState(BikeState.REQUESTED);

			return this.getBikeBean().saveBike(movement.getBike()) && this.getMovementDAO().save(movement);
		} else {
			movement.setUpdatedAt(new Timestamp((new java.util.Date()).getTime()));

			return this.getBikeBean().saveBike(movement.getBike()) && this.getMovementDAO().update(movement);
		}
	}

	public boolean confirmMovement(Movement movement) {
		movement.setState(MovementState.CONFIRMED);
		movement.getBike().setCurrentStation(null);
		movement.getBike().setState(BikeState.IN_USE);

		return this.saveMovement(movement);
	}

	public boolean cancelMovement(Movement movement) {
		movement.setState(MovementState.CANCELLED);
		movement.getBike().setState(BikeState.AVAILABLE);

		return this.saveMovement(movement);
	}

	public boolean finishMovement(Movement movement) {
		movement.setState(MovementState.FINISHED);
		movement.getBike().setCurrentStation(movement.getDestinationStation());
		movement.getBike().setState(BikeState.AVAILABLE);

		return this.saveMovement(movement);
	}

	public List<Movement> getMovements() {
		return this.getMovementDAO().findAll();
	}

	public List<Movement> getMovements(User user) {
		return this.getMovementDAO().findAllByUser(user);
	}

	public List<MovementState> getStates() {
		return Arrays.asList(MovementState.values());
	}

	public MovementDao getMovementDAO() {
		return movementDAO;
	}

	public void setMovementDAO(MovementDao movementDAO) {
		this.movementDAO = movementDAO;
	}

	public BikeBean getBikeBean() {
		return bikeBean;
	}

	public void setBikeBean(BikeBean bikeBean) {
		this.bikeBean = bikeBean;
	}

	public StationBean getStationBean() {
		return stationBean;
	}

	public void setStationBean(StationBean stationBean) {
		this.stationBean = stationBean;
	}

	public List<Movement> getActiveMovements(User user) {
		return this.getMovementDAO().findAllActive(user);
	}
}
