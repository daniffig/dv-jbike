package com.jbike.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.DatatypeConverter;

import com.jbike.model.User;
import com.jbike.persistence.UserDaoHibernate;
import com.jbike.session.UserSession;

@ManagedBean(name = "userView")
@ViewScoped
public class UserView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<User> filteredUsers;

	@ManagedProperty(value = "#{userSession.selectedUser}")
	private User user;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@PostConstruct
	public void init() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String viewForm(User user) {
		if (user == null) {
			user = new User();
		}

		this.getUserSession().setSelectedUser(user);

		return "/admin/users/form.xhtml?faces-redirect=true";
	}

	public String getFormTitle() {
		return this.getUser().isNew() ? "New User" : String.format("Edit User (%s)", this.getUser());
	}

	// FIXME
	public List<User> getUsers() {
		UserDaoHibernate udh = new UserDaoHibernate();

		return udh.findAll();
	}

	public String signIn() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		String s = "my Password";

		String password = DatatypeConverter.printHexBinary(md5.digest(s.getBytes("UTF-8"))).substring(0, 8);
		String digestedPassword = DatatypeConverter.printHexBinary(md5.digest(password.getBytes("UTF-8")));

		this.getUser().setPassword(digestedPassword);

		UserDaoHibernate udh = new UserDaoHibernate();

		udh.save(this.getUser());

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Bike successfully saved."));

		return "home";
	}

	public String logIn() {
		return "success";
	}

	public String save() {
		UserDaoHibernate udh = new UserDaoHibernate();

		if (this.getUser().isNew()) {
			udh.save(this.getUser());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully created."));
		} else {
			udh.update(user);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully updated."));
		}

		return this.backToList();
	}

	public String backToList() {
		return "/admin/users/list.xhtml?faces-redirect=true";
	}

	public void delete(User user) {
		UserDaoHibernate udh = new UserDaoHibernate();

		udh.delete(user);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully deleted."));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}
}
