package com.jbike.persistence.interfaces;

import java.util.List;

import com.jbike.model2.Penalization;

public interface PenalizationDao{

	public List<Penalization> findAllCurrentlyActive();
}
