package com.jbike.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.jbike.model2.Bike;

public class BikeDaoHibernate {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbike");
	
	public void save(Bike bike){
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(bike);
		
		try
		{
			etx.commit();
		}
		catch(NoResultException e)
		{
			if(etx.isActive()){
				etx.rollback();
			}
		}
	}
	
	public void update(Bike bike){
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		bike = em.merge(bike);
		
		try
		{
			etx.commit();
		}
		catch(NoResultException e)
		{
			if(etx.isActive()){
				etx.rollback();
			}
		}
	}
	
	public void delete(Bike bike){
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
	    em.remove(bike);
		
		try
		{
			etx.commit();
		}
		catch(NoResultException e)
		{
			if(etx.isActive()){
				etx.rollback();
			}
		}
	}
	
	public Bike findByPk(Integer id){
		EntityManager em = emf.createEntityManager();
		return em.find(Bike.class, id);
	}
}
