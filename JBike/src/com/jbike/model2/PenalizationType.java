package com.jbike.model2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="penalization_type")
public class PenalizationType {

  @Id @GeneratedValue
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;
  
  public PenalizationType() {}

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
