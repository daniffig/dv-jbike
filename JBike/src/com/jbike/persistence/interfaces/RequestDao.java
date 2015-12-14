package com.jbike.persistence.interfaces;

import com.jbike.model.RequestState;
import com.jbike.model2.Request;

import java.util.Date;
import java.util.List;

public interface RequestDao {
	public List<Request> findAllByState(RequestState state);
	
	public List<Request> findAllByDateBetween(Date from, Date to);
}
