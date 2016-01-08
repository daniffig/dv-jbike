package com.jbike.view;

import java.io.Serializable;
import java.util.List;

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

	private List<Movement> movements;

	private List<Movement> filteredMovements;

	@ManagedProperty(value = "#{userSession.selectedBike}")
	private Bike bike;

	@ManagedProperty(value = "#{userSession.selectedMovement}")
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

	public String getFormTitle() {
		return (null == this.getBike()) || this.getBike().isNew() ? "New Bike"
				: String.format("Edit Bike (%s)", this.getBike());
	}

	public String viewForm(Movement movement) {
		this.getUserSession().setSelectedMovement(movement);

		return "/user/movements/form.xhtml?faces-redirect=true";
	}

	public String save() {
		String message = this.getMovement().isNew() ? "Request successfully created." : "Request successfully updated.";

		if (this.getMovementBean().saveMovement(this.getMovement())) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while saving the request."));
		}

		return "movements/list";
	}

	public String back() {
		return this.getMovement().isNew() ? "bikes/list" : "movements/list";
	}

	public String confirmMovement(Movement movement) {
		if (this.getMovementBean().confirmMovement(movement)) {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
					"Bike successfully withdrawn. Do not forget to return it before midnight."));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while withdrawing the bike."));
		}

		return "movements/list";
	}

	public String cancelMovement(Movement movement) {
		if (this.getMovementBean().cancelMovement(movement)) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Request successfully cancelled."));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while cancelling the request."));
		}

		return "movements/list";
	}

	public String finishMovement(Movement movement) {
		if (this.getMovementBean().finishMovement(movement)) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Bike successfully returned."));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while returning the bike."));
		}

		return "movements/list";
	}

	public String reportBike(Bike bike) {
		if (this.getBikeBean().reportBike(bike)) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Bike successfully reported."));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while reporting the bike."));
		}

		return "movements/list";
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

	public List<Movement> getMovementsForLoggedUser() {
		this.setMovements(this.getUserSession().getLoggedUser().getMovements());

		return this.getMovements();
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
}
