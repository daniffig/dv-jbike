package com.jbike.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.StationDaoHibernate;

public class BikeTest {

	private StationDaoHibernate bdao = FactoryDao.getStationDao();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception{
		bdao.deleteAll();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
