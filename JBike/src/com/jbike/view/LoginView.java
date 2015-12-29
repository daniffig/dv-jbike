package com.jbike.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.jbike.helper.UserHelper;
import com.jbike.model.User;
import com.jbike.persistence.FactoryDao;
import com.jbike.session.UserSession;

@ManagedBean(name = "loginView")
@ViewScoped
public class LoginView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession.loggedUser}")
	private User user;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	/**
	 * Login operation.
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public String doLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = FactoryDao.getUserDao().authenticate(this.getUser().getEmail(),
				UserHelper.digest(this.getUser().getPassword()));

		if (null == user) {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred, please check your credentials."));

			return "login";
		}

		this.getUserSession().setLoggedUser(user);
		this.getUserSession().setIsLoggedIn(true);

		return "home";
	}

	/**
	 * Logout operation.
	 * 
	 * @return
	 */
	public String doLogout() {
		this.getUserSession().setLoggedUser(null);
		this.getUserSession().setIsLoggedIn(false);

		return "home";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}
