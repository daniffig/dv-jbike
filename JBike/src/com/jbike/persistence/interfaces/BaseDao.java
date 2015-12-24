package com.jbike.persistence.interfaces;

import java.util.List;

public interface BaseDao<T> {
	public boolean save(T obj);
	
	public boolean update(T obj);
	
	public boolean delete(T obj);
	
	public T findByPk(Long id);
	
	public List<T> findAll();
	
	public void deleteAll();
}
