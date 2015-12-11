package com.jbike.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.jbike.model2.RentStation;

public class RentStationDaoHibernate {
	public EntityManager em;
	
	public RentStationDaoHibernate(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbike");
		
		this.em = emf.createEntityManager();
	}
	
	public void save(RentStation rentStation){
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(rentStation);
		
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
	
	public void update(RentStation rentStation){
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		rentStation = em.merge(rentStation);
		
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
	
	public RentStation findByPk(Integer id){
		return em.find(RentStation.class, id);
	}
}
