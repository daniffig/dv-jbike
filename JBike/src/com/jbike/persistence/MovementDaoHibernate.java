package com.jbike.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jbike.model.Bike;
import com.jbike.model.Movement;
import com.jbike.model.MovementState;
import com.jbike.model.Station;
import com.jbike.model.User;
import com.jbike.persistence.interfaces.MovementDao;

public class MovementDaoHibernate extends BaseDaoHibernate<Movement> implements MovementDao {

	public MovementDaoHibernate() {
		super(Movement.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movement> findAllByCreatedAtBetween(Date from, Date to) {
		EntityManager em = this.getEntityManager();

		String sql = "SELECT m FROM Movement m WHERE 1=1 ";

		if (from != null) {
			sql += "AND m.createdAt > :from ";
		}

		if (to != null) {
			sql += "AND m.createdAt < :to ";
		}

		Query q = em.createQuery(sql);

		if (from != null) {
			q.setParameter("from", from);
		}
		if (to != null) {
			q.setParameter("to", to);
		}
		
		List<Movement> lm = q.getResultList();

		em.close();
		
		return lm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movement> findAllByUpdatedAtBetween(Date from, Date to) {
		EntityManager em = this.getEntityManager();

		String sql = "SELECT m FROM Movement m WHERE 1=1 ";

		if (from != null) {
			sql += "AND m.updatedAt > :from ";
		}

		if (to != null) {
			sql += "AND m.updatedAt < :to ";
		}

		Query q = em.createQuery(sql);

		if (from != null) {
			q.setParameter("from", from);
		}
		if (to != null) {
			q.setParameter("to", to);
		}

		List<Movement> lm = q.getResultList();
		
		em.close();
		
		return lm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movement> findAllUnfinished() {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.satation IS NULL");

		List<Movement> lm = query.getResultList();
		
		em.close();
		
		return lm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movement> findAllByState(MovementState state) {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.state = :state");
		query.setParameter("state", state);

		List<Movement> lm = query.getResultList();
		
		em.close();
		
		return lm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movement> findAllBySourceStation(Station station) {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.sourceStation = :station");
		query.setParameter("station", station);

		List<Movement> lm = query.getResultList();
		
		em.close();
		
		return lm;
	}

	@Override
	public Movement findOneUnfinishedByBike(Bike bike) {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery(
				"SELECT m FROM Movement m WHERE m.bike = :bike AND (m.state != :finished OR m.state != :cancelled)");
		query.setParameter("bike", bike);
		query.setParameter("finished", MovementState.FINISHED);
		query.setParameter("cancelled", MovementState.CANCELLED);

		Movement m = (Movement)query.getSingleResult();
		
		em.close();
		
		return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movement> findAllByDestinationStation(Station station) {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.destinationStation = :station");
		query.setParameter("station", station);

		List<Movement> lm = query.getResultList();
		
		em.close();
		
		return lm;
	}

	@SuppressWarnings("unchecked")
	public List<Movement> findAllActive(User user) {

		EntityManager em = this.getEntityManager();

		Query query = em.createQuery(
				"SELECT m FROM Movement m WHERE m.user = :user AND (m.state = :new OR m.state = :confirmed)");
		query.setParameter("user", user);
		query.setParameter("new", MovementState.NEW);
		query.setParameter("confirmed", MovementState.CONFIRMED);

		List<Movement> lm = query.getResultList();
		
		em.close();
		
		return lm;
	}

	@SuppressWarnings("unchecked")
	public List<Movement> findAllByUserAndState(User user, MovementState state) {

		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.user = :user AND m.state = :state ");
		query.setParameter("user", user);
		query.setParameter("state", state);

		List<Movement> lm = query.getResultList();
		
		em.close();
		
		return lm;
	}

	@SuppressWarnings("unchecked")
	public List<Movement> findAllByUser(User user) {

		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT m FROM Movement m WHERE m.user = :user");
		query.setParameter("user", user);

		List<Movement> lm = query.getResultList();
		
		em.close();
		
		return lm;
	}
}
