package com.jbike.model2;

import java.sql.Date;

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

  // TODO Add enum field "type"	
  
  @Column(nullable = false)
  private String description;

  @Column(name="created_at")
  private Date createdAt;
  
  @Column(name="end_date")
  private Date endDate;
  
  public Penalization() {}
  
  public Penalization(User user, Date endDate, String description){
	  this.user = user;
	  this.endDate = endDate;
	  this.description = description;
	  
	  this.createdAt = new Date((new java.util.Date()).getTime());
  }

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedAt() {
	return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
  }
  
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}