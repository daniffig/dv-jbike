package com.jbike.model2;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="request")
public class Request {

  @Id @GeneratedValue
  private Long id;

  @ManyToOne
  private User user;
  
  @Enumerated(EnumType.ORDINAL)
  private RequestState state;

  @ManyToOne
  private RentStation rentStation;

  @ManyToOne
  private Bike bike;
  
  private Date date;
  
  public Request() {}

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

  public void setRequestType(User user)
  {
    this.user = user;
  }
  
  public RequestState getState()
  {
    return this.state;
  }

  public void setRequestType(RequestState state)
  {
    this.state = state;
  }

  public RentStation getRentStation()
  {
    return this.rentStation;
  }

  public void setRentStation(RentStation rentStation)
  {
    this.rentStation = rentStation;
  }

  public Bike getBike()
  {
    return this.bike;
  }

  public void setBike(Bike bike)
  {
    this.bike = bike;
  }
  
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
