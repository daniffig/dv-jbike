package com.jbike.session;

import java.util.LinkedList;
import java.util.Queue;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
	
	private Queue<FacesMessage> messageQueue;
	
	@PostConstruct
	public void init() {
		this.setMessageQueue(new LinkedList<FacesMessage>());
	}

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

	public Queue<FacesMessage> getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(Queue<FacesMessage> linkedList) {
		this.messageQueue = linkedList;
	}
}
