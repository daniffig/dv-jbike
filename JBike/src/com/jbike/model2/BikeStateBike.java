package com.jbike.model2;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bike_state_state")
public class BikeStateBike {

  @Id @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name="bike_state_id")
  private BikeState bikeState;

  @ManyToOne
  private User user;

  @ManyToOne
  private Bike bike;

  private Date createdAt;
  
  public BikeStateBike() {}

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

  public BikeState getBikeState() {
    return this.bikeState;
  }

  public void setBikeState(BikeState bikeState){
    this.bikeState = bikeState;
  }

  public Bike getBike() {
    return this.bike;
  }

  public void setBike(Bike bike){
    this.bike = bike;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user){
    this.user = user;
  }
}