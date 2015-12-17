package com.jbike.persistence.interfaces;

import java.util.Date;
import java.util.List;

import com.jbike.model.Penalization;

public interface PenalizationDao extends BaseDao<Penalization>{

	public List<Penalization> findAllCurrentlyActive();
	
	public List<Penalization> findAllCreatedAtBetween(Date from, Date to);
	
	public List<Penalization> findAllEndedBetween(Date from, Date to);
}
