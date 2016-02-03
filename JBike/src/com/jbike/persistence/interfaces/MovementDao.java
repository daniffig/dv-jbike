package com.jbike.persistence.interfaces;

import java.util.Date;
import java.util.List;

import com.jbike.model.Bike;
import com.jbike.model.Movement;
import com.jbike.model.MovementState;
import com.jbike.model.Station;
import com.jbike.model.User;

public interface MovementDao extends BaseDao<Movement> {

	public List<Movement> findAllByCreatedAtBetween(Date from, Date to);

	public List<Movement> findAllByUpdatedAtBetween(Date from, Date to);

	public List<Movement> findAllBySourceStation(Station station);

	public List<Movement> findAllByDestinationStation(Station station);

	public List<Movement> findAllUnfinished();
	
	public Movement findOneUnfinishedByBike(Bike bike);

	public List<Movement> findAllByState(MovementState state);

	public List<Movement> findAllActive(User user);	

	public List<Movement> findAllByUser(User user);
}
