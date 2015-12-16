package com.jbike.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jbike.model.BikeState;
import com.jbike.model2.Bike;
import com.jbike.model2.Station;
import com.jbike.model2.StationState;
import com.jbike.persistence.BikeDaoHibernate;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.StationDaoHibernate;

public class StationTest {
	
	private StationDaoHibernate rsdao = FactoryDao.getStationDao();
	
	private BikeDaoHibernate bdao = FactoryDao.getBikeDao();
	
	Station plaza_moreno_rs;
	Station plaza_italia_rs;
	Station plaza_paso_rs;
	Station plaza_malvinas_rs;
	
	@Before
	public void setUp() throws Exception {
		bdao.deleteAll();
		rsdao.deleteAll();
		
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
		bdao.deleteAll();
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
	
	@Test
	public void testfindAllByState(){
		assertTrue(rsdao.findAllActive().size() == 4);
		
		plaza_moreno_rs.setState(StationState.UNDER_CONSTRUCTION);
		plaza_italia_rs.setState(StationState.OFFLINE);
		
		rsdao.update(plaza_moreno_rs);
		rsdao.update(plaza_italia_rs);
		
		assertFalse(rsdao.findAllActive().size() == 4);
		
		assertTrue(rsdao.findAllInactive().size() == 2);
		
		assertEquals(rsdao.findAllByState(StationState.UNDER_CONSTRUCTION).get(0), plaza_moreno_rs);
		
		assertEquals(rsdao.findAllByState(StationState.OFFLINE).get(0), plaza_italia_rs);
	}
	
	@Test
	public void testFindAllWithAvailableParkingSpace(){
		assertTrue(rsdao.findAllWithAvailableParkingSpace().size() == 4);
		
		for(int i = plaza_paso_rs.getTotalParkingSpaces(); i > 0; i--){
			bdao.save(new Bike("Brand", "Model_"+ i, plaza_paso_rs));
		}
		
		assertTrue(rsdao.findAllWithAvailableParkingSpace().size() == 3);
		assertFalse(rsdao.findAllWithAvailableParkingSpace().contains(plaza_paso_rs));
	}
	
	@Test
	public void testFindOneByName(){
		assertEquals(rsdao.findOneByName("Plaza Moreno"), plaza_moreno_rs);
		
		assertNull(rsdao.findOneByName("InexistentStation"));
	}

}
