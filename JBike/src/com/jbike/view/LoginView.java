package com.jbike.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jbike.helper.UserHelper;
import com.jbike.model.User;
import com.jbike.navigation.NavigationBean;
import com.jbike.persistence.FactoryDao;

@ManagedBean(name = "loginView")
@ViewScoped
public class LoginView implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession.loggedUser}")
	private User user;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	/**
	 * Login operation.
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public String doLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		this.setUser(FactoryDao.getUserDao().authenticate(this.user.getEmail(), UserHelper.digest(this.user.getPassword())));

		if (this.getUser() != null) {
			return navigationBean.redirectToWelcome();
		}
		this.setUser(new User());
		
		// Set login ERROR
		FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// To to login page
		return navigationBean.toLogin();
	}

	/**
	 * Logout operation.
	 * 
	 * @return
	 */
	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		this.setUser(new User());

		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return navigationBean.toLogin();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

}
