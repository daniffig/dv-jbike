package com.jbike.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "penalization")
public class Penalization {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	// TODO Add enum field "type"

	@Column(nullable = false)
	private String description;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "end_date")
	private Timestamp endDate;

	public Penalization() {
	}

	public Penalization(User user, Timestamp endDate, String description) {
		this.user = user;
		this.endDate = endDate;
		this.description = description;

		this.createdAt = new Timestamp((new java.util.Date()).getTime());
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
	public boolean isNew(){
		return (this.id==null);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Penalization other = (Penalization) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}