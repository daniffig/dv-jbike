package com.jbike.model2;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bike")
public class Bike {

  @Id @GeneratedValue
  private Long id;

  private Date createdAt;

  private String brand;

  private String model;

  @ManyToOne
  @JoinColumn(nullable=true)
  private Station rentStation;

  @OneToOne
  @JoinColumn(nullable=true)
  private User user;

  @Enumerated(EnumType.ORDINAL)
  private BikeState state;
  
  public Bike() {}

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
  
  public String getBrand() {
	return brand;
  }

  public void setBrand(String brand) {
	this.brand = brand;
  }

  public String getModel() {
	return model;
  }

  public void setModel(String model) {
	this.model = model;
  }

  public Station getRentStation() {
    return rentStation;
  }

  public void setRentStation(Station rentStation) {
    this.rentStation = rentStation;
  }

  public User getUser()
  {
    return this.user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public BikeState getState() {
    return state;
  }

  public void setState(BikeState state) {
    this.state = state;
  }
}


