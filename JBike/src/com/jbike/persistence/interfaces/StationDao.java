package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model.Station;
import com.jbike.model.StationState;

public interface StationDao  extends BaseDao<Station>{
	public Station findOneByName(String name);
	
	public List<Station> findAllByState(StationState state);
	
	public List<Station> findAllActive();
	
	public List<Station> findAllInactive();
	
	public List<Station> findAllWithAvailableParkingSpace();
}
