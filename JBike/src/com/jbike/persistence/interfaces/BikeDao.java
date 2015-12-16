package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model2.Bike;
import com.jbike.model2.BikeState;

public interface BikeDao {
	public Bike findOneByCode(String code);
	
	public List<Bike> findAllByName(String name);
	
	public List<Bike> findAllByState(BikeState state);
	
	public List<Bike> findAllRented();
}
