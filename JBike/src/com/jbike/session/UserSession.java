package com.jbike.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jbike.model.Bike;
import com.jbike.model.Station;
import com.jbike.model.User;

@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession {

	private Bike selectedBike;
	private Station selectedStation;
	private User selectedUser;

	public Bike getSelectedBike() {
		return selectedBike;
	}

	public void setSelectedBike(Bike selectedBike) {
		this.selectedBike = selectedBike;
	}

	public Station getSelectedStation() {
		return selectedStation;
	}

	public void setSelectedStation(Station selectedStation) {
		this.selectedStation = selectedStation;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
}
