package com.jbike.model2;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="movement")
public class Movement {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne
	private User user;
	  
	@ManyToOne
	private Station station;

	@ManyToOne
	private Bike bike;
	 
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	  
	public Movement() {}

	public Long getId() {
	  return id;
	}

	private void setId(Long id) {
	  this.id = id;
	}

	public User getUser()
	{
	  return this.user;
	}
	
	public void setUser(User user)
	{
	  this.user = user;
	}

	public Station getStation()
	{
	  return this.station;
	}

	public void setStation(Station station)
	{
	  this.station = station;
	}

	public Bike getBike()
	{
	  return this.bike;
	}

	public void setBike(Bike bike)
	{
	  this.bike = bike;
	}
	  
	public Date getCreatedAt() {
	  return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
	  this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
	  return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
	  this.updatedAt = updatedAt;
	}
}