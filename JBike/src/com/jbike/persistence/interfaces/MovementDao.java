package com.jbike.persistence.interfaces;

import com.jbike.model2.Movement;

import java.util.Date;
import java.util.List;

public interface MovementDao extends BaseDao<Movement>{

	public List<Movement> findAllByDateBetween(Date from, Date to);
}
