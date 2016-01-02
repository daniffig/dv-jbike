package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model.User;

public interface UserDao  extends BaseDao<User>{
	public User authenticate(String email, String password);
	
	public User findOneByEmail(String email);
	
	public User findOneByDni(Long dni);
	
	public List<User> findAllActive();
	
	public List<User> findAllAdmin();
	
	//Agregar alguno con penalizaciones activas
	//Agregar alguno por requests activos
}
