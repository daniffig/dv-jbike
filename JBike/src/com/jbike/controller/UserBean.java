package com.jbike.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

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

	@PostConstruct
	public void init() {
		this.setUserDAO(FactoryDao.getUserDao());
	}
	
	public List<Gender> getGenders() {
		return Arrays.asList(Gender.values());
	}

	public boolean saveUser(User user) {
		if (user.isNew()) {
			return this.getUserDAO().save(user);
		} else {
			return this.getUserDAO().update(user);
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
}
