package com.jbike.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Request {

	private RequestState state;
	private Date createdAt;
	private int createdBy;
	private Date updatedAt;
	private int updatedBy;

	public static final Map<Integer, Request> requests;

	static {
		requests = new HashMap<Integer, Request>();

		Random random = new Random();
		
		requests.put(1, new Request(RequestState.NEW, new Date()));
	}

	public Request(RequestState state, Date createdAt) {
		this.setState(state);
		this.setCreatedAt(createdAt);
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
}
