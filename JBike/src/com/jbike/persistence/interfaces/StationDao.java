package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model2.Station;
import com.jbike.model2.StationState;

public interface StationDao {
	public Station findOneByName(String name);
	
	public List<Station> findAllByState(StationState state);
	
	public List<Station> findAllActive();
	
	public List<Station> findAllInactive();
	
	public List<Station> findAllWithAvailableParkingSpace();
}
