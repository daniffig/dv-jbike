package com.jbike.persistence.interfaces;

import com.jbike.model.RequestState;
import com.jbike.model2.Request;
import java.util.List;

public interface RequestDao {
	public List<Request> findAllByState(RequestState state);
}
