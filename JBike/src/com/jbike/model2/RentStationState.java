package com.jbike.model2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rent_station_state")
public class RentStationState {

  @Id @GeneratedValue
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;
  
  public RentStationState() {}

  public Long getId() {
    return id;
  }

  private void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return this.getName();
  }
}
