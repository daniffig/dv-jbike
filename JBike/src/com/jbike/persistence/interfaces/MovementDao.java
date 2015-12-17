package com.jbike.persistence.interfaces;

import java.util.Date;
import java.util.List;

import com.jbike.model.Movement;

public interface MovementDao extends BaseDao<Movement>{

	public List<Movement> findAllByDateBetween(Date from, Date to);
}
