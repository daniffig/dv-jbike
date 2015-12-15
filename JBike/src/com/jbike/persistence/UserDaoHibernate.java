package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model2.Station;
import com.jbike.model2.User;
import com.jbike.persistence.interfaces.UserDao;

public class UserDaoHibernate extends BaseDaoHibernate<User> implements UserDao{

	public UserDaoHibernate(){
		super(User.class);
	}

	@Override
	public User findOneByEmail(String email) {
		EntityManager em = this.getEntityManager();
		
		User u;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
			query.setParameter("email", email);
			u = (User)query.getSingleResult();
		}
		catch(NoResultException e)
		{
			u = null;
		}
		em.close();
		
		return u;
	}

	@Override
	public List<User> findAllActive() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT u FROM User u WHERE u.active = TRUE");
		
		return (List<User>) query.getResultList();
	}

	@Override
	public List<User> findAllAdmin() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT u FROM User u WHERE u.isAdmin = TRUE");
		
		return (List<User>) query.getResultList();
	}
}
