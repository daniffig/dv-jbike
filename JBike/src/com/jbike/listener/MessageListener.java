package com.jbike.listener;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.jbike.session.UserSession;

@ManagedBean(name = "messageBean")
public class MessageListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499286553587940133L;

	@ManagedProperty("#{userSession}")
	private UserSession userSession;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		while (!this.getUserSession().getMessageQueue().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, this.getUserSession().getMessageQueue().poll());
		}
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}
