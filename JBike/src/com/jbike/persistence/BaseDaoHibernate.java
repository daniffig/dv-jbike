package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.EntityExistsException;

import com.jbike.persistence.interfaces.BaseDao;

public class BaseDaoHibernate<T> implements BaseDao<T> {
	private Class<T> persistentClass;

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbike");

	public BaseDaoHibernate(Class<T> pc) {
		persistentClass = pc;
	}

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	

	public boolean save(T obj) {
		EntityManager em = this.getEntityManager();

		EntityTransaction etx = em.getTransaction();
		etx.begin();

		em.persist(obj);

		try {
			etx.commit();
		} catch (NoResultException | EntityExistsException e) {
			if (etx.isActive()) {
				etx.rollback();
			}
			return false;
		}
		em.close();

		return true;
	}

	public boolean update(T obj) {
		EntityManager em = this.getEntityManager();

		EntityTransaction etx = em.getTransaction();
		etx.begin();

		obj = em.merge(obj);

		try {
			etx.commit();
		} catch (NoResultException e) {
			if (etx.isActive()) {
				etx.rollback();
			}
			
			return false;
		}

		em.close();
		
		return true;
	}

	public boolean delete(T obj) {
		EntityManager em = this.getEntityManager();

		EntityTransaction etx = em.getTransaction();
		etx.begin();

		em.remove(em.contains(obj) ? obj : em.merge(obj));

		try {
			etx.commit();
		} catch (NoResultException e) {
			if (etx.isActive()) {
				etx.rollback();
			}
			return false;
		}

		em.close();
		return true;
	}

	public T findByPk(Long id) {
		EntityManager em = this.getEntityManager();

		T rs;

		try {
			rs = (T) em.find(persistentClass, id);
		} catch (NoResultException e) {
			rs = null;
		}

		em.close();

		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		EntityManager em = this.getEntityManager();

		Query query = em.createQuery("SELECT e FROM " + persistentClass.getSimpleName() + " e");

		List<T> lrs = (List<T>) query.getResultList();

		em.close();

		return lrs;
	}
	
	public void deleteAll(){
		EntityManager em = this.getEntityManager();
		EntityTransaction etx = em.getTransaction();	
			etx.begin();

			Query q = em.createQuery("DELETE FROM " + persistentClass.getSimpleName());

			q.executeUpdate();
	    try{	
			etx.commit();
		} catch (IllegalStateException | PersistenceException e) {
			if(etx.isActive()){
				etx.rollback();
			}
		}
	    em.close();
	}
}
