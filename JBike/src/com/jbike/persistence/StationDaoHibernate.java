package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model2.Station;
import com.jbike.model2.StationState;
import com.jbike.persistence.interfaces.StationDao;

public class StationDaoHibernate extends BaseDaoHibernate<Station> implements StationDao{
	
	public StationDaoHibernate(){
		super(Station.class);
	}
	
	public Station findOneByName(String name){
		EntityManager em = this.getEntityManager();
		
		Station rs;
		
		try
		{
			Query q = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.name = :name");
			q.setParameter("name", name);
			rs = (Station)q.getSingleResult();
		}
		catch(NoResultException e)
		{
			rs = null;
		}
		em.close();
		
		return rs;
	}
	
	
	public List<Station> findAllActive(){
		return findAllByState(StationState.IN_OPERATION);
	}


	@Override
	public List<Station> findAllByState(StationState state) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.state = :active_state");
		query.setParameter("active_state", state);
		
		return (List<Station>) query.getResultList();
	}

	@Override
	public List<Station> findAllInactive() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT rs FROM RentStation rs WHERE rs.state != :active_state");
		query.setParameter("active_state", StationState.IN_OPERATION);
		
		return (List<Station>) query.getResultList();
	}

	@Override
	public List<Station> findAllWithAvailableParkingSpace() {
		// TODO Auto-generated method stub
		return null;
	}
}
