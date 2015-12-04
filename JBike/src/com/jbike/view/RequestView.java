package com.jbike.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.jbike.controller.RequestBean;
import com.jbike.model.Request;
import com.jbike.model.RequestState;

@ManagedBean(name = "requestView")
@ViewScoped
public class RequestView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Request> requests;
	private List<Request> filteredRequests;

	@ManagedProperty("#{requestBean}")
	private RequestBean requestBean;

	@PostConstruct
	public void init() {
		System.out.println("jajajajaj");
		requests = requestBean.getRequests();
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public RequestBean getRequestBean() {
		return requestBean;
	}

	public List<SelectItem> getStateOptions() {
		List<SelectItem> options = new ArrayList<SelectItem>();

		options.add(new SelectItem("", "Select One"));

		for (RequestState state : RequestState.values()) {
			options.add(new SelectItem(state));
		}

		return options;
	}

	public List<Request> getFilteredRequests() {
		return filteredRequests;
	}

	public void setFilteredRequests(List<Request> filteredRequests) {
		this.filteredRequests = filteredRequests;
	}
}
