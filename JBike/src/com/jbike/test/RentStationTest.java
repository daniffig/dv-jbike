package com.jbike.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jbike.model2.RentStation;
import com.jbike.persistence.RentStationDaoHibernate;

public class RentStationTest {

	@Test
	public void testSave() {
		RentStationDaoHibernate rsdao= new RentStationDaoHibernate();
		
		RentStation rent_station_test = new RentStation("Plaza Moreno", 30);
		rsdao.save(rent_station_test);
		
		assertTrue(rsdao.em.contains(rent_station_test));	
	}

}
