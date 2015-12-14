package com.jbike.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jbike.model2.Station;

public class BaseDaoHibernate<T> {
	private Class<T> persistentClass;
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbike");
	
	public BaseDaoHibernate(Class<T> pc)
	{
		persistentClass = pc;
	}
	
	protected EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	public void save(T obj){
		EntityManager em = this.getEntityManager();
		
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(obj);
		
		try
		{
			etx.commit();
		}
		catch(NoResultException e)
		{
			if(etx.isActive()){
				etx.rollback();
			}
		}
		em.close();
	}
	
	public void update(T obj){
		EntityManager em = this.getEntityManager();
	
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		obj = em.merge(obj);
		
		try
		{
			etx.commit();
		}
		catch(NoResultException e)
		{
			if(etx.isActive()){
				etx.rollback();
			}
		}
		
		em.close();
	}
	
	public void delete(T obj){
		EntityManager em = this.getEntityManager();
		
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.remove(em.contains(obj) ? obj : em.merge(obj));
		
		try
		{
			etx.commit();
		}
		catch(NoResultException e)
		{
			if(etx.isActive()){
				etx.rollback();
			}
		}
		
		em.close();
	}
	
	public T findByPk(Integer id){
		EntityManager em = this.getEntityManager();
		
		T rs;
		
		try
		{
			rs =  (T) em.find(persistentClass, id);
		}
		catch(NoResultException e)
		{
			rs = null;
		}
		
		em.close();
		
		return rs;
	}
	
	public List<T> findAll(){
		EntityManager em = this.getEntityManager();
		
		Query query = em.createQuery("SELECT rs FROM "+ persistentClass.getSimpleName() +" rs");
	    
		List<T> lrs = (List<T>) query.getResultList();
		
		em.close();
		
		return lrs;
	}
}
