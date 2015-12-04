package com.jbike.model2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="penalization")
public class Penalization {
  
  @Id @GeneratedValue
  private Long id;

  @ManyToOne
  private User user;

  @ManyToOne
  private PenalizationType penalizationType;
  
  @Column(nullable = false)
  private String description;

  private Date endDate;


  public Penalization() {}

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

  public PenalizationType getPenalizationType()
  {
    return this.penalizationType;
  }

  public void setPenalizationType(PenalizationType penalizationType)
  {
    this.penalizationType = penalizationType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}