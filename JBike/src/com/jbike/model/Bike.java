package com.jbike.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bike")
public class Bike {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "updated_by")
	private User updatedBy;

	private String name;

	@Column(unique = true)
	private String code;

	@ManyToOne
	@JoinColumn(name = "current_station_id", nullable = true)
	private Station currentStation;

	@OneToMany(mappedBy = "bike")
	private List<Movement> movements;

	@Enumerated(EnumType.ORDINAL)
	private BikeState state;

	public Bike() {
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Timestamp((new java.util.Date()).getTime());
	}

	public Bike(String code, String name) {
		this.code = code;
		this.name = name;
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Timestamp((new java.util.Date()).getTime());
	}

	public Bike(String code, String name, Station station) {
		this.code = code;
		this.name = name;
		this.currentStation = station;
		this.state = BikeState.AVAILABLE;
		this.createdAt = new Timestamp((new java.util.Date()).getTime());
	}

	public String toString() {
		return this.getName();
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
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
		return currentStation;
	}

	public void setCurrentStation(Station station) {
		this.currentStation = station;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public BikeState getState() {
		return state;
	}

	public void setState(BikeState state) {
		this.state = state;
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
		Bike other = (Bike) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isRented() {
		return (this.currentStation == null);
	}

	public boolean canBeRequested() {
		return this.getState().canBeRequested() && !this.isRented();
	}

	public boolean canBeDeleted() {
		boolean hasMovements = this.getMovements().size() > 0;

		return !hasMovements;
	}

	public boolean canBeReported() {
		return this.getState().canBeReported();
	}
}
