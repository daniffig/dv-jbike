package com.jbike.persistence;

import java.util.List;


import com.jbike.model2.Bike;
import com.jbike.model2.BikeState;
import com.jbike.persistence.interfaces.BikeDao;

public class BikeDaoHibernate extends BaseDaoHibernate<Bike> implements BikeDao{
	
	public BikeDaoHibernate(){
		super(Bike.class);
	}

	@Override
	public List<Bike> findAllByBrand(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bike> findAllByModel(String model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bike> findAllByState(BikeState state) {
		// TODO Auto-generated method stub
		return null;
	}
}
