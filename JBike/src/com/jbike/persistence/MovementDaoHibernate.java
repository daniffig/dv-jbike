package com.jbike.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jbike.model.Movement;
import com.jbike.model.MovementState;
import com.jbike.persistence.interfaces.MovementDao;

public class MovementDaoHibernate extends BaseDaoHibernate<Movement> implements MovementDao {

	public MovementDaoHibernate() {
		super(Movement.class);
	}

	@Override
	public List<Movement> findAllByCreatedAtBetween(Date from, Date to) {
		EntityManager em = this.getEntityManager();
		
		String sql = "SELECT m FROM Movement m WHERE 1=1 ";
		
		if(from != null){
			sql += "AND m.createdAt > :from ";
		}
		
		if(to != null){
			sql += "AND m.createdAt < :to ";
		}
		
		Query q = em.createQuery(sql);
		
		if(from != null){
			q.setParameter("from", from);
		}
		if(to != null){
			q.setParameter("to", to);
		}
		
		return (List<Movement>) q.getResultList();
	}

	@Override
	public List<Movement> findAllUnfinished() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.satation IS NULL");
		
		return (List<Movement>) query.getResultList();
	}
	
	@Override
	public List<Movement> findAllByState(MovementState state) {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.state = :state");
		query.setParameter("state", state);
		
		return (List<Movement>) query.getResultList();
	}

}
