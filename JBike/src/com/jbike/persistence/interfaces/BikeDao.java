package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model.Bike;
import com.jbike.model.BikeState;

public interface BikeDao extends BaseDao<Bike>{
	public Bike findOneByCode(String code);
	
	public List<Bike> findAllByName(String name);
	
	public List<Bike> findAllByState(BikeState state);
	
	public List<Bike> findAllRented();
}
