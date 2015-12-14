package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model2.Bike;
import com.jbike.model2.BikeState;

public interface BikeDao {
	public List<Bike> findAllByBrand(String brand);
	
	public List<Bike> findAllByModel(String model);
	
	public List<Bike> findAllByState(BikeState state);
	
}
