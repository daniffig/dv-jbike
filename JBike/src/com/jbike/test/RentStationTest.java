package com.jbike.test;

import static org.junit.Assert.*;

import javax.persistence.Query;

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
	
	@Test
	public void testUpdate() {
		RentStationDaoHibernate rsdao= new RentStationDaoHibernate();
		
		RentStation rent_station_test = new RentStation("Plaza Moreno", 30);
		rsdao.save(rent_station_test);
		
		assertTrue(rsdao.em.contains(rent_station_test));	
		
		Query q = rsdao.em.createQuery("SELECT rs FROM RentStation rs");
		
		q.setFirstResult(0);
		
		rent_station_test = (RentStation)q.getSingleResult();
		
		rent_station_test.setName("Plaza San Martin");
		
		rsdao.update(rent_station_test);
		
		assertTrue(rent_station_test.getName() == "Plaza San Martin");	
	}
	
	@Test
	public void testDelete(){
		RentStationDaoHibernate rsdao= new RentStationDaoHibernate();
		
		RentStation rent_station_test = new RentStation("Plaza Moreno", 30);
		rsdao.save(rent_station_test);
		
		assertTrue(rsdao.em.contains(rent_station_test));	
		
		rsdao.delete(rent_station_test);
		
		assertFalse(rsdao.em.contains(rent_station_test));
	}


}
