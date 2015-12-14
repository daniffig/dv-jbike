package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jbike.model2.Bike;
import com.jbike.model2.BikeState;
import com.jbike.model2.User;
import com.jbike.persistence.interfaces.BikeDao;

public class BikeDaoHibernate extends BaseDaoHibernate<Bike> implements BikeDao{
	
	public BikeDaoHibernate(){
		super(Bike.class);
	}

	@Override
	public List<Bike> findAllByBrand(String brand) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.brand = :brand");
		query.setParameter("brand", brand);
		
		return (List<Bike>) query.getResultList();
	}

	@Override
	public List<Bike> findAllByModel(String model) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.model = :model");
		query.setParameter("model", model);
		
		return (List<Bike>) query.getResultList();
	}

	@Override
	public List<Bike> findAllByState(BikeState state) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.state = :state");
		query.setParameter("state", state);
		
		return (List<Bike>) query.getResultList();
	}
	
	public List<Bike> findAllRented(){
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.station IS NULL");
		
		return (List<Bike>) query.getResultList();
	}
}
