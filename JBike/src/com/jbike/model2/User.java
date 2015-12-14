package com.jbike.model2;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = true)
	private String password;

	@Column(name = "is_active")
	private Boolean active;

	@Column(name = "is_admin")
	private Boolean isAdmin;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL })
	private Profile profile;

	@OneToMany(mappedBy = "user")
	private List<Movement> movements;

	@OneToMany(mappedBy = "user")
	private List<Penalization> penalizations;

	public User() {
		this.active = true;
	}

	public User(String email) {
		this.email = email;
		this.active = true;

		this.profile = new Profile();
	}

	public User(String email, Profile profile) {
		this.email = email;
		this.profile = profile;

		this.active = true;
	}

	// ToDo add md5 hashing to password
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.active = true;

		this.profile = new Profile();
		/*
		 * MessageDigest md = MessageDigest.getInstance("MD5");
		 * md.update(password.getBytes(Charset.forName("UTF-8"))); byte[]
		 * digested_password = md.digest(); ...
		 */
	}

	// ToDo add md5 hashing to password
	public User(String email, String password, Profile profile) {
		this.email = email;
		this.password = password;
		this.active = true;

		this.profile = profile;
		/*
		 * MessageDigest md = MessageDigest.getInstance("MD5");
		 * md.update(password.getBytes(Charset.forName("UTF-8"))); byte[]
		 * digested_password = md.digest(); ...
		 */
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public List<Penalization> getPenalizations() {
		return penalizations;
	}

	public void setPenalizations(List<Penalization> penalizations) {
		this.penalizations = penalizations;
	}

	public String toString() {
		return this.getEmail();
	}

}
