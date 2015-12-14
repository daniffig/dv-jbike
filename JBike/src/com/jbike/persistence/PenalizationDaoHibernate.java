package com.jbike.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jbike.model2.Penalization;
import com.jbike.persistence.interfaces.PenalizationDao;

public class PenalizationDaoHibernate extends BaseDaoHibernate<Penalization> implements PenalizationDao{
	
	public PenalizationDaoHibernate(){
		super(Penalization.class);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Penalization> findAllEndedBetween(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}
}
