package com.jbike.persistence.interfaces;

import java.util.List;

public interface BaseDao<T> {
	public boolean save(T obj);
	
	public boolean update(T obj);
	
	public void delete(T obj);
	
	public T findByPk(Integer id);
	
	public List<T> findAll();
	
	public void deleteAll();
}