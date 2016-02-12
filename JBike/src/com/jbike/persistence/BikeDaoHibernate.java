package com.jbike.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model.Bike;
import com.jbike.model.BikeState;
import com.jbike.persistence.interfaces.BikeDao;

public class BikeDaoHibernate extends BaseDaoHibernate<Bike> implements BikeDao {

	public BikeDaoHibernate() {
		super(Bike.class);
	}

	public Bike findOneByCode(String code) {
		EntityManager em = this.getEntityManager();

		Bike b;

		try {
			Query q = em.createQuery("SELECT b FROM Bike b WHERE b.code = :code");
			q.setParameter("code", code);
			b = (Bike) q.getSingleResult();
		} catch (NoResultException e) {
			b = null;
		}
		em.close();

		return b;
	}

	@SuppressWarnings("unchecked")
	public List<Bike> findAllByName(String name) {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.name = :name");
		query.setParameter("name", name);
		
		List<Bike> lb = query.getResultList();
		
		em.close();

		return lb;
	}

	@SuppressWarnings("unchecked")
	public List<Bike> findAllByState(BikeState state) {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.state = :state");
		query.setParameter("state", state);
		
		List<Bike> lb = query.getResultList();
		
		em.close();

		return lb;
	}

	@SuppressWarnings("unchecked")
	public List<Bike> findAllRented() {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT b FROM Bike b WHERE b.currentStation IS NULL");
		
		List<Bike> lb = query.getResultList();

		em.close();
		
		return lb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bike> findAllCreatedAtBetween(Date from, Date to) {
		EntityManager em = this.getEntityManager();

		String sql = "SELECT b FROM Bike b WHERE 1=1 ";

		if (from != null) {
			sql += "AND b.createdAt > :from ";
		}

		if (to != null) {
			sql += "AND b.createdAt < :to ";
		}

		Query q = em.createQuery(sql);

		if (from != null) {
			q.setParameter("from", from);
		}
		if (to != null) {
			q.setParameter("to", to);
		}

		List<Bike> lb = q.getResultList();
		
		em.close();
		
		return lb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bike> findAllUpdatedAtBetween(Date from, Date to) {
		EntityManager em = this.getEntityManager();

		String sql = "SELECT b FROM Bike b WHERE 1=1 ";

		if (from != null) {
			sql += "AND b.updatedAt > :from ";
		}

		if (to != null) {
			sql += "AND b.updatedAt < :to ";
		}

		Query q = em.createQuery(sql);

		if (from != null) {
			q.setParameter("from", from);
		}
		if (to != null) {
			q.setParameter("to", to);
		}

		List<Bike> lb = q.getResultList();
		
		em.close();
		
		return lb;
	}
}
