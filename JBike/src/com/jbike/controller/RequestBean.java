package com.jbike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.jbike.model.Bike;
import com.jbike.model.Request;
import com.jbike.navigation.NavigationBean;

@ManagedBean(name = "requestBean")
@ApplicationScoped
public class RequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Request> requests;

	@ManagedProperty("#{navigationBean}")
	private NavigationBean navigationBean;

	@PostConstruct
	public void init() {
		requests = new ArrayList<Request>(Request.requests.values());
	}

	public void requestBike(Bike bike) {
		navigationBean.redirect("/requests/list");
	}

	public void cancelRequest(Request request) {
		if (request.cancel()) {
			navigationBean.redirect("/requests/list");
		}
	}

	public void approveRequest(Request request) {
		if (request.approve()) {
			navigationBean.redirect("/requests/admin-list");
		}
	}

	public void rejectRequest(Request request) {
		if (request.reject()) {
			navigationBean.redirect("/requests/admin-list");
		}
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

}
