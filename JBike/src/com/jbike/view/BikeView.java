package com.jbike.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.jbike.controller.BikeBean;
import com.jbike.model.Bike;
import com.jbike.model.Movement;
import com.jbike.session.UserSession;

@ManagedBean(name = "bikeView")
@ViewScoped
public class BikeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Bike> filteredBikes;

	@ManagedProperty(value = "#{userSession.selectedBike}")
	private Bike bike;

	@ManagedProperty("#{bikeBean}")
	private BikeBean bikeBean;

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

	public String viewHistory(Bike bike) {
		this.getUserSession().setSelectedBike(bike);

		return "bikes/history";
	}

	public String viewForm(Bike bike) {
		if (bike == null) {
			bike = new Bike();
		}

		this.getUserSession().setSelectedBike(bike);

		return "/admin/bikes/form.xhtml?faces-redirect=true";
	}

	public String delete(Bike bike) {
		if (this.getBikeBean().deleteBike(bike)) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Bike successfully deleted."));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while deleting the bike."));
		}

		return "bikes/list";
	}

	// FIXME
	public String requestBike(Bike bike) {
		if (bike.canBeRequested()) {
			this.getUserSession().setSelectedMovement(new Movement(this.getUserSession().getLoggedUser(), bike));

			return "/user/movements/form.xhtml?faces-redirect=true";
		}

		return "bikes/list";
	}

	public List<Bike> getBikes() {
		return this.getBikeBean().getBikes();
	}

	public String save() {
		String message = this.getBike().isNew() ? "Bike successfully created." : "Bike successfully updated.";

		if (this.getBikeBean().saveBike(this.getBike())) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while saving the bike."));
		}

		return "bikes/list";
	}

	public BikeBean getBikeBean() {
		return bikeBean;
	}

	public void setBikeBean(BikeBean bikeBean) {
		this.bikeBean = bikeBean;
	}

	public List<Bike> getFilteredBikes() {
		if (null == filteredBikes) {
			this.setFilteredBikes(this.getBikes());
		}

		return filteredBikes;
	}

	public void setFilteredBikes(List<Bike> filteredBikes) {
		this.filteredBikes = filteredBikes;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}
}
