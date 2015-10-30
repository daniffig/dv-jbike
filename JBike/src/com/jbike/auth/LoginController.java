package com.jbike.auth;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jbike.model.User;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginController {
	
	private List<User> users;

	private String email;
	private String password;
	
	@PostConstruct
	public void init() {
		List<User> users = new ArrayList<User>();
		
		for (int i = 1; i <= 10; i++) {
			users.add(new User(i, "mail" + i, "password" + i));
		}
	}
	
	public String login() {
		return null;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
