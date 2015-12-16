package com.jbike.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jbike.model2.Station;
import com.jbike.model2.Bike;
import com.jbike.model2.BikeState;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.StationDaoHibernate;
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
		
		bike_1 = new Bike("Fire Bird", "Bin19175", s_plaza_moreno);
		bike_2 = new Bike("Kawasaki", "Kst310", s_plaza_moreno);
		bike_3 = new Bike("Kawasaki", "Kht101", s_plaza_moreno);
		bike_4 = new Bike("Venzo", "Raptor", s_plaza_moreno);
		
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
/*
	@Test
	public void testFindAllByBrand() {
		assertTrue(bdao.findAllByBrand("Fire Bird").size() == 1);
		
		assertTrue(bdao.findAllByBrand("Kawasaki").size() == 2);
		
		assertTrue(bdao.findAllByBrand("InexistentBrand").isEmpty());
	}
	
	@Test
	public void testFindAllByModel() {
		List<Bike> bl = bdao.findAllByModel("Raptor");
		
		assertTrue(bl.size() == 1);
		assertEquals(bl.get(0), bike_4);
		assertEquals(bl.get(0).getModel(), bike_4.getModel());
		assertEquals(bl.get(0).getBrand(), bike_4.getBrand());
		
		assertTrue(bdao.findAllByModel("").isEmpty());
	}
*/
	@Test
	public void testFindAllByState() {
		assertEquals(bdao.findAllByState(BikeState.AVAILABLE).size(), 4);
		
		bike_1.setState(BikeState.IN_USE);
		bike_2.setState(BikeState.REPORTED);
		
		bdao.update(bike_1);
		bdao.update(bike_2);
		
		assertNotSame(4, bdao.findAllByState(BikeState.AVAILABLE).size());
		
		List<Bike> bl_in_use = bdao.findAllByState(BikeState.IN_USE);
		
		assertEquals(bl_in_use.size(), 1);
		assertEquals(bl_in_use.get(0), bike_1);
		
		List<Bike> bl_reported = bdao.findAllByState(BikeState.REPORTED);
		
		assertEquals(bl_reported.size(), 1);
		assertEquals(bl_reported.get(0), bike_2);
		
		bdao.delete(bike_3);
		
		assertEquals(bdao.findAllByState(BikeState.AVAILABLE).size(), 1);
	}
	
	@Test
	public void testFindAllRented() {
		assertTrue(bdao.findAllRented().size() == 0);
		
		bike_1.setCurrentStation(null);
		bdao.update(bike_1);
		
		assertTrue(bdao.findAllRented().size() == 1);
		assertTrue(bdao.findAllRented().contains(bike_1));
	}
}
