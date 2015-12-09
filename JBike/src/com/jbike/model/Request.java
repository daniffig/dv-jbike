package com.jbike.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Bike bike;
	private RequestState state;
	private Date createdAt;
	private Date updatedAt;
	private User creator;
	private User updater;

	public static final Map<Integer, Request> requests;

	static {
		requests = new HashMap<Integer, Request>();

		requests.put(1, new Request(1, Bike.bikes.get(1), RequestState.NEW, new Date()));
		requests.put(2, new Request(2, Bike.bikes.get(2), RequestState.APPROVED, new Date()));
	}

	public Request(int id, Bike bike, RequestState state, Date createdAt) {
		this.setId(id);
		this.setBike(bike);
		this.setState(state);
		this.setCreatedAt(createdAt);
	}

	public String toString() {
		return state.getName();
	}

	public boolean canBeCancelled() {
		return this.state.canBeCancelled();
	}
	
	public boolean canBeApproved() {
		return this.state.canBeApproved();
	}
	
	public boolean canBeRejected() {
		return this.state.canBeRejected();
	}
	
	public boolean cancel() {
		System.out.println("Voy a cancelar");
		this.setState(RequestState.CANCELLED);
		
		return true;
	}
	
	public boolean approve() {
		this.setState(RequestState.APPROVED);
		
		return true;
	}
	
	public boolean reject() {
		this.setState(RequestState.REJECTED);
		
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RequestState getState() {
		return state;
	}

	public void setState(RequestState state) {
		this.state = state;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

}
