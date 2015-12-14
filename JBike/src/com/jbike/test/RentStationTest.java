package com.jbike.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jbike.model2.RentStation;
import com.jbike.model2.RentStationState;
import com.jbike.persistence.RentStationDaoHibernate;

public class RentStationTest {
	
	private RentStationDaoHibernate rsdao;
	
	RentStation plaza_moreno_rs;
	RentStation plaza_italia_rs;
	RentStation plaza_paso_rs;
	RentStation plaza_malvinas_rs;
	
	@Before
	public void setUp() throws Exception {
		
		rsdao= new RentStationDaoHibernate();
		
		plaza_moreno_rs   = new RentStation("Plaza Moreno", 30);
		plaza_italia_rs   = new RentStation("Plaza Italia", 20);
		plaza_paso_rs     = new RentStation("Plaza Paso", 10);
		plaza_malvinas_rs = new RentStation("Plaza Malvinas", 20);
		
		rsdao.save(plaza_moreno_rs);
		rsdao.save(plaza_italia_rs);
		rsdao.save(plaza_paso_rs);
		rsdao.save(plaza_malvinas_rs);
	}
	
	@After
	public void tearDown() throws Exception{
		for(RentStation rs : rsdao.findAll()){
			rsdao.delete(rs);
		}
	}
	

	@Test
	public void testUpdate() {
		RentStation rent_station_test = rsdao.findOneByName("Plaza Moreno");
		
		rent_station_test.setName("Plaza San Martin");
		
		rsdao.update(rent_station_test);
		
		assertTrue(rent_station_test.getName() == "Plaza San Martin");	
	}
	
	@Test
	public void testDelete(){
		rsdao.delete(plaza_moreno_rs);
		
		assertNull(rsdao.findOneByName("Plaza Moreno"));
	}
	
	@Test
	public void testFindAll(){	
		assertTrue(rsdao.findAll().size() == 4);
	}
	
	public void testGetInOperation(){
		RentStation rent_station_test = rsdao.findOneByName("Plaza Moreno");
		
		rent_station_test.setState(RentStationState.OFFLINE);
		
		rsdao.update(rent_station_test);
		
		assertTrue(rsdao.findAllActive().size() == 3);
	}

}
