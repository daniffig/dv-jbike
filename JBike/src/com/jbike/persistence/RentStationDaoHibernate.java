package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model2.RentStation;
import com.jbike.model2.RentStationState;
import com.jbike.persistence.interfaces.RentStationDao;

public class RentStationDaoHibernate extends BaseDaoHibernate<RentStation> implements RentStationDao{
	
	public RentStationDaoHibernate(){
		super(RentStation.class);
	}
	
	public RentStation findOneByName(String name){
		EntityManager em = this.getEntityManager();
		
		RentStation rs;
		
		try
		{
			Query q = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.name = :name");
			q.setParameter("name", name);
			rs = (RentStation)q.getSingleResult();
		}
		catch(NoResultException e)
		{
			rs = null;
		}
		em.close();
		
		return rs;
	}
	
	
	public List<RentStation> findAllActive(){
		return findAllByState(RentStationState.IN_OPERATION);
	}


	@Override
	public List<RentStation> findAllByState(RentStationState state) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.state = :active_state");
		query.setParameter("active_state", state);
		
		return (List<RentStation>) query.getResultList();
	}

	@Override
	public List<RentStation> findAllInactive() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.state != :active_state");
		query.setParameter("active_state", RentStationState.IN_OPERATION);
		
		return (List<RentStation>) query.getResultList();
	}

	@Override
	public List<RentStation> findAllWithAvailableParkingSpace() {
		// TODO Auto-generated method stub
		return null;
	}
}
