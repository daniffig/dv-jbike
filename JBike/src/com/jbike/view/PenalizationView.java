package com.jbike.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.jbike.controller.PenalizationBean;
import com.jbike.model.Penalization;
import com.jbike.model.User;
import com.jbike.session.UserSession;

@ManagedBean(name = "penalizationView")
@ViewScoped
public class PenalizationView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9061051521587518635L;

	private List<Penalization> penalizations;

	private List<Penalization> filteredPenalizations;

	@ManagedProperty(value = "#{userSession.selectedPenalization}")
	private Penalization penalization;

	@ManagedProperty("#{penalizationBean}")
	private PenalizationBean penalizationBean;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String getFormTitle() {
		return (null == this.getPenalization()) || this.getPenalization().isNew() ? "New Penalization"
				: String.format("Edit Penalization (%s)", this.getPenalization());
	}

	public String viewForm(Penalization penalization) {
		if (penalization == null) {
			penalization = new Penalization(this.getUserSession().getSelectedUser());
		}

		this.getUserSession().setSelectedPenalization(penalization);

		return "penalizations/form";
	}

	public String save() {
		String message = this.getPenalization().isNew() ? "Penalization successfully created."
				: "Penalization successfully updated.";

		if (this.getPenalizationBean().savePenalization(this.getPenalization())) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while saving the penalization."));
		}

		return "users/penalizations";
	}

	public String delete(Penalization penalization) {
		if (this.getPenalizationBean().deletePenalization(penalization)) {
			this.getUserSession().getMessageQueue().offer(
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Penalization successfully deleted."));
		} else {

			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while deleting the penalization."));
		}

		return "users/penalizations";
	}

	public PenalizationBean getPenalizationBean() {
		return penalizationBean;
	}

	public void setPenalizationBean(PenalizationBean penalizationBean) {
		this.penalizationBean = penalizationBean;
	}

	public List<Penalization> getFilteredPenalizations() {
		if (null == filteredPenalizations) {
			this.setFilteredPenalizations(this.getPenalizations());
		}

		return filteredPenalizations;
	}

	public void setFilteredPenalizations(List<Penalization> filteredPenalizations) {
		this.filteredPenalizations = filteredPenalizations;
	}

	public Penalization getPenalization() {
		return penalization;
	}

	public void setPenalization(Penalization penalization) {
		this.penalization = penalization;
	}

	public List<Penalization> getPenalizationsForSelectedUser() {
		this.setPenalizations(this.getPenalizations(this.getUserSession().getSelectedUser()));

		return this.getPenalizations();
	}

	public List<Penalization> getPenalizationsForLoggedUser() {
		this.setPenalizations(this.getPenalizations(this.getUserSession().getLoggedUser()));

		return this.getPenalizations();
	}

	private List<Penalization> getPenalizations(User user) {
		return this.getPenalizationBean().getPenalizations(user);
	}

	public void setPenalizations(List<Penalization> penalizations) {
		this.penalizations = penalizations;
	}

	public List<Penalization> getPenalizations() {
		return this.penalizations;
	}
}
