package com.jbike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.jbike.model.Bike;
import com.jbike.model.Request;

@ManagedBean(name = "requestBean")
@ApplicationScoped
public class RequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Request> requests;
	private Request request;

	@PostConstruct
	public void init() {
		requests = new ArrayList<Request>(Request.requests.values());
	}

	public void requestBike(Bike bike) {

		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/requests/list.xhtml");
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
}
