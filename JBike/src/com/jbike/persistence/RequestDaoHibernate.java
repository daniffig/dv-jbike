package com.jbike.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import com.jbike.model.RequestState;
import com.jbike.model2.Request;
import com.jbike.persistence.interfaces.RequestDao;

public class RequestDaoHibernate extends BaseDaoHibernate<Request> implements RequestDao{

	public RequestDaoHibernate(){
		super(Request.class);
	}
	
	@Override
	public List<Request> findAllByState(RequestState state) {
		EntityManager em = this.getEntityManager();
		
		Query q = em.createQuery("SELECT r FROM Request r WHERE r.state = :state");
		q.setParameter("state", state);
		
		return (List<Request>) q.getResultList();
	}

	@Override
	public List<Request> findAllByDateBetween(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

}
