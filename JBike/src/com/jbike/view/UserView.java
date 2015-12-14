package com.jbike.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.xml.bind.DatatypeConverter;

import org.primefaces.context.RequestContext;

import com.jbike.model.Station;
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
		this.setUser(new User());
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

	// TODO Ver bien cómo aprovechar esto.
	public void openSignInDialog() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		RequestContext.getCurrentInstance().openDialog("/users/sign-in.xhtml", options, null);
	}

	public void viewProfile() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		RequestContext.getCurrentInstance().openDialog("/admin/users/profile", options, null);
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
