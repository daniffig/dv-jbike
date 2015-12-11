package com.jbike.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.jbike.model2.Bike;
import com.jbike.model2.RentStation;

public class BikeDaoHibernate {
public EntityManager em;
	
	public BikeDaoHibernate(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbike");
		
		this.em = emf.createEntityManager();
	}
	
	public void save(Bike bike){
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
		return em.find(Bike.class, id);
	}
}
