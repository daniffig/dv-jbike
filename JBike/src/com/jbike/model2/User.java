package com.jbike.model2;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

  @Id @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(name="is_active")
  private Boolean active;

  @Column(name="is_admin")
  private Boolean isAdmin;

  @OneToOne(mappedBy = "user", cascade={CascadeType.ALL})
  private Profile profile;

  @OneToMany(mappedBy="user")
  private List<Request> requests;

  @OneToMany(mappedBy="user")
  private List<Penalization> penalizations;

  public User() {}

  //ToDo add md5 hashing to password
  public User(String email, String password)
  {
    this.email    = email;
    this.password = password;
    this.active   = true;

    /*
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes(Charset.forName("UTF-8")));
      byte[] digested_password = md.digest();
      ...
    */
  }

  public Long getId() {
    return id;
  }

  private void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean isActive() {
    return active;
  }

  private void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean isAdmin() {
    return isAdmin;
  }

  private void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public List<Request> getRequests() {
    return requests;
  }

  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }

  public List<Penalization> getPenalizations() {
    return penalizations;
  }

  public void setPenalizations(List<Penalization> penalizations) {
    this.penalizations = penalizations;
  }

  public String toString() {
    return this.getEmail();
  }
  
}
