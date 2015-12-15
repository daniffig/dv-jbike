package com.jbike.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jbike.model2.User;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.UserDaoHibernate;

public class UserTest {

	private UserDaoHibernate udao = FactoryDao.getUserDao();
	
	User u_andres;
	User u_lucio;
	User u_jorge;
	User u_matias;
	
	@Before
	public void setUp() throws Exception {
		
		udao.deleteAll();
		
		u_andres = new User("andres.cimadamore@gmail.com");
		u_lucio  = new User("lucio.digiacomo@gmail.com");
		u_jorge  = new User("jorge.rosso@gmail.com");
		u_matias = new User("matias.arrech@gmail.com");
		
		udao.save(u_andres);
		udao.save(u_lucio);
		udao.save(u_jorge);
		udao.save(u_matias);
	}
	
	@After
	public void tearDown() throws Exception{
		udao.deleteAll();
	}

	@Test
	public void test() {
		assertTrue(true);
	}

}
