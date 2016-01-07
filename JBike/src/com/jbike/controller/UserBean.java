package com.jbike.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.jbike.model.Gender;
import com.jbike.model.User;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.interfaces.UserDao;

@ManagedBean(name = "userBean")
@ApplicationScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7421057914797478637L;

	private UserDao userDAO;

	@ManagedProperty(value = "#{penalizationBean}")
	private PenalizationBean penalizationBean;

	@PostConstruct
	public void init() {
		this.setUserDAO(FactoryDao.getUserDao());
	}

	public List<Gender> getGenders() {
		return Arrays.asList(Gender.values());
	}

	public boolean userExists(User user) {
		return this.getUserDAO().findOneByDni(user.getProfile().getDni()) != null
				|| this.getUserDAO().findOneByEmail(user.getEmail()) != null;
	}

	public boolean saveUser(User user) {
		if (user.isNew()) {
			return this.getUserDAO().save(user);
		} else {
			return this.getUserDAO().update(user);
		}
	}

	public boolean deleteUser(User user) {
		if (user.canBeDeleted()) {
			return this.getUserDAO().delete(user);
		} else {
			return false;
		}
	}

	public List<User> getUsers() {
		return this.getUserDAO().findAll();
	}

	public UserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	public PenalizationBean getPenalizationBean() {
		return penalizationBean;
	}

	public void setPenalizationBean(PenalizationBean penalizationBean) {
		this.penalizationBean = penalizationBean;
	}

	public boolean canRequestBikes(User user) {
		return this.getPenalizationBean().getActivePenalizations(user).size() == 0;
	}
}
