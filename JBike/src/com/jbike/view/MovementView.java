package com.jbike.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.jbike.controller.BikeBean;
import com.jbike.controller.MovementBean;
import com.jbike.model.Bike;
import com.jbike.model.Movement;
import com.jbike.session.UserSession;

@ManagedBean(name = "movementView")
@ViewScoped
public class MovementView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7446961008889911519L;

	private List<Movement> filteredMovements;

	@ManagedProperty(value = "#{userSession.selectedBike}")
	private Bike bike;

	private Movement movement;

	@ManagedProperty("#{bikeBean}")
	private BikeBean bikeBean;

	@ManagedProperty("#{movementBean}")
	private MovementBean movementBean;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	@PostConstruct
	public void init() {
		// FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public String getFormTitle() {
		return (null == this.getBike()) || this.getBike().isNew() ? "New Bike"
				: String.format("Edit Bike (%s)", this.getBike());
	}

	public String save() {		
		String message = this.getMovement().isNew() ? "Bike successfully created." : "Bike successfully updated.";

		if (this.getMovementBean().saveMovement(this.getMovement())) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while saving the bike."));
		}

		return "movements/list";
	}

	// FIXME
	public String requestBike(Bike bike) {
		if (bike.canBeRequested()) {
			return "/movements/form.xhtml?faces-redirect=true";
		}

		return "/bikes/list.xhtml?faces-redirect=true";
	}

	public List<Bike> getBikes() {
		return this.getBikeBean().getBikes();
	}

	public BikeBean getBikeBean() {
		return bikeBean;
	}

	public void setBikeBean(BikeBean bikeBean) {
		this.bikeBean = bikeBean;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public List<Movement> getFilteredMovements() {
		return filteredMovements;
	}

	public void setFilteredMovements(List<Movement> filteredMovements) {
		this.filteredMovements = filteredMovements;
	}

	public Movement getMovement() {
		if (null == movement) {
			movement = new Movement();
			
			movement.setBike(this.getBike());
		}
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public MovementBean getMovementBean() {
		return movementBean;
	}

	public void setMovementBean(MovementBean movementBean) {
		this.movementBean = movementBean;
	}
}
