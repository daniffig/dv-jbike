package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model2.User;

public interface UserDao {
	public User findOneByEmail(String email);
	
	public List<User> findAllActive();
	
	public List<User> findAllAdmins();
}
