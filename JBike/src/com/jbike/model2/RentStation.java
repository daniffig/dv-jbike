package com.jbike.model2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rent_station")
public class RentStation {

  @Id @GeneratedValue
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @ManyToOne
  private RentStationState state;
  
  private Double latitude;

  private Double longitude;
  
  @Column(name="total_parking_spaces")
  private Integer totalParkingSpaces;

  @OneToMany(mappedBy="rentStation")
  private List<Bike> bikes;
  
  public RentStation() {}

  public RentStation(String name) {
    this.name = name;
  }

  public RentStation(String name, Integer totalParkingSpaces) {
    this.name = name;
    this.totalParkingSpaces = totalParkingSpaces;
  }

  public RentStation(String name, Integer totalParkingSpaces, Double latitude, Double longitude) {
    this.name               = name;
    this.totalParkingSpaces = totalParkingSpaces;
    this.latitude           = latitude;
    this.longitude          = longitude;
  }

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

  public RentStationState getState()
  {
    return this.state;
  }

  public void setState(RentStationState state)
  {
    this.state = state;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Integer getTotalParkingSpaces() {
    return totalParkingSpaces;
  }

  public void setTotalParkingSpaces(Integer totalParkingSpaces) {
    this.totalParkingSpaces = totalParkingSpaces;
  }

  public List<Bike> getBikes() {
    return bikes;
  }

  public void setBikes(List<Bike> bikes) {
    this.bikes = bikes;
  }

  public String toString() {
    return this.getName();
  }
}