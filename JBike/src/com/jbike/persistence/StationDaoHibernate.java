package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model.Penalization;
import com.jbike.model.Station;
import com.jbike.model.StationState;
import com.jbike.persistence.interfaces.StationDao;

public class StationDaoHibernate extends BaseDaoHibernate<Station> implements StationDao{
	
	public StationDaoHibernate(){
		super(Station.class);
	}
	
	public Station findOneByName(String name){
		EntityManager em = this.getEntityManager();
		
		Station s;
		
		try
		{
			Query q = em.createQuery("SELECT s FROM Station s WHERE s.name = :name");
			q.setParameter("name", name);
			s = (Station)q.getSingleResult();
		}
		catch(NoResultException e)
		{
			s = null;
		}
		em.close();
		
		return s;
	}
	
	
	public List<Station> findAllActive(){
		return findAllByState(StationState.IN_OPERATION);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findAllByState(StationState state) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT s FROM Station s WHERE s.state = :active_state");
		query.setParameter("active_state", state);
		
		List<Station> ls = query.getResultList();
		
		em.close();
		
		return ls;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findAllInactive() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT s FROM Station s WHERE s.state != :active_state");
		query.setParameter("active_state", StationState.IN_OPERATION);
		
		List<Station> ls = query.getResultList();
		
		em.close();
		
		return ls;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findAllWithAvailableParkingSpace() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT s FROM Station s WHERE s.totalParkingSpaces > (SELECT COUNT(b.id) FROM Bike b WHERE b.station = s)");
		
		List<Station> ls = query.getResultList();
		
		em.close();
		
		return ls;
	}
}
