package com.jbike.model;

import java.util.HashMap;
import java.util.Map;

public class User {

	private Long id;
	private String email;
	private String password;
	private boolean isActive;

	public static final Map<Integer, User> users;

	static {
		users = new HashMap<Integer, User>();

		users.put(1, new User(1L, "lucio.digiacomo@slyt.gba.gov.ar", "password", true));
		users.put(2, new User(1L, "andres.cimadamore@slyt.gba.gov.ar", "password", true));
	}

	public User() {

	}

	public User(Long id, String email, String password, boolean isActive) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.isActive = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
