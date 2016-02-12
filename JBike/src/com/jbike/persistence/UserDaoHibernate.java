package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jbike.model.Station;
import com.jbike.model.User;
import com.jbike.persistence.interfaces.UserDao;

public class UserDaoHibernate extends BaseDaoHibernate<User> implements UserDao{

	public UserDaoHibernate(){
		super(User.class);
	}

	@Override
	public User authenticate(String email, String password){
		EntityManager em = this.getEntityManager();

		User u;
		try
		{
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.active = TRUE");
			query.setParameter("email", email);
			query.setParameter("password", password);
			
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
	public User findOneByDni(Long dni) {
		EntityManager em = this.getEntityManager();
		
		User u;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM User u WHERE u.profile.dni = :dni");
			query.setParameter("dni", dni);
			u = (User)query.getSingleResult();
		}
		catch(NoResultException e)
		{
			u = null;
		}
		em.close();
		
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllActive() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT u FROM User u WHERE u.active = TRUE");
		
		List<User> lu = query.getResultList();
		
		em.close();
		
		return lu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllAdmin() {
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT u FROM User u WHERE u.isAdmin = TRUE");
		
		List<User> lu = query.getResultList();
		
		em.close();
		
		return lu;
	}
}
