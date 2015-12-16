package com.jbike.model2;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bike")
public class Bike {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@ManyToOne
    @JoinColumn(name="created_by")
	private User createdBy;
	
	@ManyToOne
    @JoinColumn(name="updated_by")
	private User updatedBy;

	private String name;
	
	@Column(unique = true)
	private String code;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Station station;

	@Enumerated(EnumType.ORDINAL)
	private BikeState state;

	public Bike() {
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Date((new java.util.Date()).getTime());
	}

	public Bike(String code, String name) {
		this.code  = code;
		this.name  = name;
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Date((new java.util.Date()).getTime());
	}

	public Bike(String code, String name, Station station) {
		this.code = code;
		this.name = name;
		this.station = station;
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Date((new java.util.Date()).getTime());
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Station getCurrentStation() {
		return station;
	}

	public void setCurrentStation(Station station) {
		this.station = station;
	}

	public BikeState getState() {
		return state;
	}

	public void setState(BikeState state) {
		this.state = state;
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
		Bike other = (Bike) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean isRented(){
		return (this.station == null);
	}
}
