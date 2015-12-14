package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model2.RentStation;
import com.jbike.model2.RentStationState;

public interface RentStationDao {
	public RentStation findOneByName(String name);
	
	public List<RentStation> findAllByState(RentStationState state);
	
	public List<RentStation> findAllActive();
	
	public List<RentStation> findAllInactive();
	
	public List<RentStation> findAllWithAvailableParkingSpace();
}
