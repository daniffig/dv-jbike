package com.jbike.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jbike.model2.Station;
import com.jbike.model2.StationState;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.StationDaoHibernate;

public class StationTest {
	
	private StationDaoHibernate rsdao = FactoryDao.getStationDao();
	
	Station plaza_moreno_rs;
	Station plaza_italia_rs;
	Station plaza_paso_rs;
	Station plaza_malvinas_rs;
	
	@Before
	public void setUp() throws Exception {
		
		plaza_moreno_rs   = new Station("Plaza Moreno", 30);
		plaza_italia_rs   = new Station("Plaza Italia", 20);
		plaza_paso_rs     = new Station("Plaza Paso", 10);
		plaza_malvinas_rs = new Station("Plaza Malvinas", 20);
		
		rsdao.save(plaza_moreno_rs);
		rsdao.save(plaza_italia_rs);
		rsdao.save(plaza_paso_rs);
		rsdao.save(plaza_malvinas_rs);
	}
	
	@After
	public void tearDown() throws Exception{
		rsdao.deleteAll();
	}
	

	@Test
	public void testUpdate() {
		Station rent_station_test = rsdao.findOneByName("Plaza Moreno");
		
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
		Station rent_station_test = rsdao.findOneByName("Plaza Moreno");
		
		rent_station_test.setState(StationState.OFFLINE);
		
		rsdao.update(rent_station_test);
		
		assertTrue(rsdao.findAllActive().size() == 3);
	}

}
