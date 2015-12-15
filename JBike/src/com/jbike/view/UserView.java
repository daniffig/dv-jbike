package com.jbike.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.DatatypeConverter;

import org.primefaces.context.RequestContext;

import com.jbike.model2.User;
import com.jbike.persistence.UserDaoHibernate;

@ManagedBean(name = "userView")
@ViewScoped
public class UserView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<User> filteredUsers;

	private User user;

	@PostConstruct
	public void init() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		this.setUser((session.get("user") == null) ? new User() : (User) session.get("user"));

		session.remove("user");
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

	public Map<String, Object> getFormOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("closable", false);
		options.put("draggable", false);
		options.put("modal", true);
		options.put("resizable", false);

		return options;
	}

	public void openNewUserDialog() {
		RequestContext.getCurrentInstance().openDialog("/admin/users/form", this.getFormOptions(), null);
	}

	public void openEditUserDialog(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);

		RequestContext.getCurrentInstance().openDialog("/admin/users/form", this.getFormOptions(), null);
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void save() {
		UserDaoHibernate udh = new UserDaoHibernate();

		if (this.getUser().isNew()) {
			udh.save(this.getUser());
		} else {
			udh.update(user);
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));

		RequestContext.getCurrentInstance().closeDialog(null);
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
