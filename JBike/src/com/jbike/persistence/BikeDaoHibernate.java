package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model2.Bike;
import com.jbike.model2.BikeState;
import com.jbike.persistence.interfaces.BikeDao;

public class BikeDaoHibernate extends BaseDaoHibernate<Bike> implements BikeDao{
	
	public BikeDaoHibernate(){
		super(Bike.class);
	}

	public Bike findOneByCode(String code){
		EntityManager em = this.getEntityManager();
		
		Bike b;
		
		try
		{
			Query q = em.createQuery("SELECT b FROM Bike b WHERE b.code = :code");
			q.setParameter("code", code);
			b = (Bike)q.getSingleResult();
		}
		catch(NoResultException e)
		{
			b = null;
		}
		em.close();
		
		return b;
	}

	public List<Bike> findAllByName(String name) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.name = :name");
		query.setParameter("name", name);
		
		return (List<Bike>) query.getResultList();
	}

	public List<Bike> findAllByState(BikeState state) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.state = :state");
		query.setParameter("state", state);
		
		return (List<Bike>) query.getResultList();
	}
	
	public List<Bike> findAllRented(){
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.currentStation IS NULL");
		
		return (List<Bike>) query.getResultList();
	}
}
