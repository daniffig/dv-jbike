package com.jbike.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.jbike.controller.BikeBean;
import com.jbike.model.Bike;
import com.jbike.model.BikeState;
import com.jbike.session.UserSession;

@ManagedBean(name = "bikeView")
@RequestScoped
public class BikeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Bike> filteredBikes;

	@ManagedProperty(value = "#{userSession.selectedBike}")
	private Bike bike;

	private int currentStationId;

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
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public String getFormTitle() {
		return this.getBike().isNew() ? "New Bike" : String.format("Edit Bike (%s)", this.getBike());
	}

	public String viewForm(Bike bike) {
		if (bike == null) {
			bike = new Bike();
			
			bike.setState(BikeState.AVAILABLE);
		}

		this.getUserSession().setSelectedBike(bike);

		return "/admin/bikes/form.xhtml?faces-redirect=true";
	}

	public List<Bike> getBikes() {
		return this.getBikeBean().getBikes();
	}

	public String save() {
		String message = this.getBike().isNew() ? "Bike successfully created." : "Bike successfully updated.";

		if (this.getBikeBean().saveBike(this.getBike())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while saving the bike."));
		}

		return "/admin/bikes/list.xhtml?faces-redirect=true";
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

	public List<Bike> getFilteredBikes() {
		return filteredBikes;
	}

	public void setFilteredBikes(List<Bike> filteredBikes) {
		this.filteredBikes = filteredBikes;
	}

	public int getCurrentStationId() {
		return currentStationId;
	}

	public void setCurrentStationId(int currentStationId) {
		this.currentStationId = currentStationId;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}
}
