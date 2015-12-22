package com.jbike.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.jbike.controller.BikeBean;
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

	@PostConstruct
	public void init() {
		// FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public String getFormTitle() {
		return (null == this.getBike()) || this.getBike().isNew() ? "New Bike"
				: String.format("Edit Bike (%s)", this.getBike());
	}

	public String save() {

		return "/movements/list.xhtml?faces-redirect=true";
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

	public String backToList() {
		return "/admin/bikes/list.xhtml?faces-redirect=true";
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
}
