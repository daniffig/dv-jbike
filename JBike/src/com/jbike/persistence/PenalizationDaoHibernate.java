package com.jbike.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model.Penalization;
import com.jbike.model.Station;
import com.jbike.model.User;
import com.jbike.persistence.interfaces.PenalizationDao;

public class PenalizationDaoHibernate extends BaseDaoHibernate<Penalization> implements PenalizationDao{
	
	public PenalizationDaoHibernate(){
		super(Penalization.class);
	}

	@Override
	public Penalization findLast(User user){
		EntityManager em = this.getEntityManager();
		
		Penalization p;
		
		try
		{
			Query q = em.createQuery("SELECT p FROM Penalization p WHERE user = :user ORDER BY p.createdAt DESC LIMIT 1");
			q.setParameter("user", user);
			p = (Penalization)q.getSingleResult();
		}
		catch(NoResultException e)
		{
			p = null;
		}
		em.close();
		
		return p;
	}
	
	@Override
	public List<Penalization> findAllCurrentlyActive() {
		EntityManager em = this.getEntityManager();
		
		Query q = em.createQuery("SELECT p FROM Penalization p WHERE p.endDate > :date");
		q.setParameter("date", new Date());
		
		return (List<Penalization>) q.getResultList();
	}

	@Override
	public List<Penalization> findAllCreatedAtBetween(Date from, Date to) {
		EntityManager em = this.getEntityManager();
		
		String sql = "SELECT p FROM Penalization p WHERE 1=1 ";
		
		if(from != null){
			sql += "AND p.createdAt > :from ";
		}
		
		if(to != null){
			sql += "AND p.createdAt < :to ";
		}
		
		Query q = em.createQuery(sql);
		
		if(from != null){
			q.setParameter("from", from);
		}
		if(to != null){
			q.setParameter("to", to);
		}
		
		return (List<Penalization>) q.getResultList();
	}

	@Override
	public List<Penalization> findAllEndedBetween(Date from, Date to) {
		EntityManager em = this.getEntityManager();
		
		String sql = "SELECT p FROM Penalization p WHERE 1=1 ";
		
		if(from != null){
			sql += "AND p.endDate > :from ";
		}
		
		if(to != null){
			sql += "AND p.endDate < :to ";
		}
		
		Query q = em.createQuery(sql);
		
		if(from != null){
			q.setParameter("from", from);
		}
		if(to != null){
			q.setParameter("to", to);
		}
		
		return (List<Penalization>) q.getResultList();
	}
}
