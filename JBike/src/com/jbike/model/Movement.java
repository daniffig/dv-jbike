package com.jbike.model;

import java.sql.Timestamp;

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
@Table(name = "movement")
public class Movement {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	@JoinColumn(name = "destination_station_id", nullable = true)
	private Station destinationStation;
	
	@ManyToOne
	@JoinColumn(name = "source_station_id", nullable = true)
	private Station sourceStation;

	@ManyToOne
	private Bike bike;

	@Enumerated(EnumType.ORDINAL)
	private MovementState state;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public Movement() {
		this.createdAt = new Timestamp((new java.util.Date()).getTime());
		this.state = MovementState.NEW;
	}

	public Movement(User user) {
		this.createdAt = new Timestamp((new java.util.Date()).getTime());
		this.user = user;
		this.state = MovementState.NEW;
	}

	public Movement(User user, Bike bike) {
		this.createdAt = new Timestamp((new java.util.Date()).getTime());
		this.user = user;
		this.bike = bike;
		this.state = MovementState.NEW;
		this.sourceStation = bike.getCurrentStation();
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Station getDestinationStation() {
		return this.destinationStation;
	}

	public void setDestinationStation(Station destinationStation) {
		this.destinationStation = destinationStation;
	}

	public Station getSourceStation() {
		return this.sourceStation;
	}

	public void setSourceStation(Station sourceStation) {
		this.sourceStation = sourceStation;
	}
	
	public Bike getBike() {
		return this.bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public MovementState getState() {
		return this.state;
	}

	public void setState(MovementState state) {
		this.state = state;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movement other = (Movement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean canBeConfirmed() {
		return this.getState().canBeConfirmed();
	}

	public boolean canBeCancelled() {
		return this.getState().canBeCancelled();
	}

	public boolean canBeFinished() {
		return this.getState().canBeFinished();
	}

	public boolean canBeEdited() {
		return this.getState().canBeEdited();
	}
}
