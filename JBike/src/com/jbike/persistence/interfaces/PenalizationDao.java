package com.jbike.persistence.interfaces;

import java.util.Date;
import java.util.List;

import com.jbike.model2.Penalization;

public interface PenalizationDao{

	public List<Penalization> findAllCurrentlyActive();
	
	public List<Penalization> findAllCreatedAtBetween(Date from, Date to);
	
	public List<Penalization> findAllEndedBetween(Date from, Date to);
}
