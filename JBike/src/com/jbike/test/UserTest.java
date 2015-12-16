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
	
	@Test
	public void testFindOneByEmail(){
		assertNull(udao.findOneByEmail("inexistentmail@something.com"));
		
		assertEquals(udao.findOneByEmail("andres.cimadamore@gmail.com"), u_andres);
		
		u_andres.setEmail("cimadamore.andres@gmail.com");
		udao.update(u_andres);
		
		assertNull(udao.findOneByEmail("andres.cimadamore@gmail.com"));
		
		assertEquals(udao.findOneByEmail("cimadamore.andres@gmail.com"), u_andres);
	}

	@Test 
	public void testFindAllActive(){
		u_andres.setActive(false);
		u_lucio.setActive(false);
		
		udao.update(u_andres);
		udao.update(u_lucio);
		
		assertTrue(udao.findAllActive().size() == 2);
		
		u_andres.setActive(true);
		u_lucio.setActive(true);
		
		udao.update(u_andres);
		udao.update(u_lucio);
		
		assertTrue(udao.findAllActive().size() == 4);
	}

	@Test 
	public void testFindAllAdmin(){
		u_andres.setIsAdmin(true);
		u_lucio.setIsAdmin(true);
		
		udao.update(u_andres);
		udao.update(u_lucio);
		
		assertTrue(udao.findAllAdmin().size() == 2);
		
		u_andres.setIsAdmin(false);
		u_lucio.setIsAdmin(false);
		
		udao.update(u_andres);
		udao.update(u_lucio);
		
		assertTrue(udao.findAllAdmin().isEmpty());
	}

}
