package com.jbike.test;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.StationDaoHibernate;
import com.jbike.model.Bike;
import com.jbike.model.BikeState;
import com.jbike.model.Station;
import com.jbike.persistence.BikeDaoHibernate;

public class BikeTest {

	private BikeDaoHibernate bdao = FactoryDao.getBikeDao();
	private StationDaoHibernate sdao = FactoryDao.getStationDao();
	
	private	Bike bike_1;
	private Bike bike_2;
	private Bike bike_3;
	private Bike bike_4;
		
	@Before
	public void setUp() throws Exception {
		bdao.deleteAll();
		sdao.deleteAll();
		
		Station s_plaza_moreno = new Station("Plaza Moreno");
		sdao.save(s_plaza_moreno);
		
		bike_1 = new Bike("fire_bird_1", "Fire Bird Bin19175", s_plaza_moreno);
		bike_2 = new Bike("kawasaki_1", "Kawasaki Kst310", s_plaza_moreno);
		bike_3 = new Bike("kawasaki_2", "kawasaki Kht101", s_plaza_moreno);
		bike_4 = new Bike("venzo_1", "Venzo Raptor", s_plaza_moreno);
		
		bdao.save(bike_1);
		bdao.save(bike_2);
		bdao.save(bike_3);
		bdao.save(bike_4);
	}

	@After
	public void tearDown() throws Exception{
		bdao.deleteAll();
		sdao.deleteAll();
	}
	
	@Test
	public void testFindOneByCode(){
		assertEquals(bdao.findOneByCode("fire_bird_1"), bike_1);
		
		assertNull(bdao.findOneByCode("an_inexistent_code"));
	}
	
	@Test
	public void testFindAllByName(){
		assertTrue(bdao.findAllByName("Venzo Raptor").size() == 1);
		
		assertTrue(bdao.findAllByName("inexistent_name").isEmpty());
		
		bike_2.setName("Kawasaki 1");
		bike_3.setName("Kawasaki 1");
		
		bdao.update(bike_2);
		bdao.update(bike_3);
		
		assertTrue(bdao.findAllByName("Kawasaki 1").size() == 2);
	}
	
	@Test
	public void testFindAllByState(){
		assertTrue(bdao.findAllByState(BikeState.AVAILABLE).size() == 4);
		
		assertTrue(bdao.findAllByState(BikeState.UNDER_MAINTENANCE).isEmpty());
		
		bike_2.setState(BikeState.UNDER_MAINTENANCE);
		bdao.update(bike_2);
		
		assertTrue(bdao.findAllByState(BikeState.AVAILABLE).size() == 3);
		
		assertTrue(bdao.findAllByState(BikeState.UNDER_MAINTENANCE).size() == 1);
	}
}
