package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jbike.model2.RentStation;
import com.jbike.model2.RentStationState;

public class RentStationDaoHibernate {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbike");
	
	private static EntityManager em = emf.createEntityManager();
	
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
	
	public void delete(RentStation rentStation){
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
	    em.remove(rentStation);
		
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
	
	public RentStation findByName(String name){
		Query q = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.name = :name");
		q.setParameter("name", name);
		RentStation rs = (RentStation)q.getSingleResult();
			
		return rs;
	}
	
	public List<RentStation> getAll(){
		Query query = em.createQuery("SELECT rs FROM RentStation rs");
	    
		return (List<RentStation>) query.getResultList();
	}
	
	public List<RentStation> getActive(){
		Query query = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.state = :active_state");
		query.setParameter("active_state", RentStationState.IN_OPERATION);
		
		return (List<RentStation>) query.getResultList();
	}
	
	public boolean exists(RentStation rentStation){
		return em.contains(rentStation);
	}
}
