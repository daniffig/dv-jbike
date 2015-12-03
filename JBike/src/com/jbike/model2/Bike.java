package com.jbike.model2;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
  
  @ManyToOne
  @JoinColumn(nullable=true)
  private RentStation rentStation;

  @OneToOne
  @JoinColumn(nullable=true)
  private User user;

  @OneToMany(mappedBy="bike")
  private List<BikeStateBike> states;
  
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

  public RentStation getRentStation() {
    return rentStation;
  }

  public void setRentStation(RentStation rentStation) {
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

  public List<BikeStateBike> getStates() {
    return states;
  }

  public void setStates(List<BikeStateBike> states) {
    this.states = states;
  }
}


