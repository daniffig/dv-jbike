package com.jbike.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class Profile {

  @Id @GeneratedValue
  private Long id;

  @OneToOne(mappedBy = "profile")
  private User user;

  @Column(unique = true, nullable = true)
  private Long dni;

  @Column(name="first_name", nullable = true)
  private String firstName;

  @Column(name="last_name", nullable = true)
  private String lastName;

  @Column(nullable = true)
  private String address;

  @Column(name="birth_date")
  private Date birthDate;

  @Enumerated(EnumType.ORDINAL)
  private Gender gender;

  
  public Profile() {}
  
  public Profile(Long dni, String firstName, String lastName){
	  this.dni = dni;
	  this.firstName = firstName;
	  this.lastName = lastName;
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

  public Long getDni() {
    return dni;
  }

  public void setDni(Long dni) {
    this.dni = dni;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  public boolean isNew(){
	return (this.id==null);
  }

  @Override
  public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Profile other = (Profile) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
  }
}
