package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model.User;

public interface UserDao  extends BaseDao<User>{
	public User findOneByEmail(String email);
	
	public List<User> findAllActive();
	
	public List<User> findAllAdmin();
	
	//Agregar alguno con penalizaciones activas
	//Agregar alguno por requests activos
}
