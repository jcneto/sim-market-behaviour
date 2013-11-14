package app.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import app.persistence.HibernateUtil;


abstract class GenericDAOImpl <entity, PK extends Serializable> implements GenericDAO <entity, PK> {
	protected Session session;	
	
	
	@Override
	public entity save(entity obj) {		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();        
        session.save(obj);
        session.getTransaction().commit();
		return obj;

	}
	
	
	@Override
	public void update(entity obj) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
	};
	
	@Override
	public void delete(entity obj) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
		
	};
	
	@Override
	public List<entity> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
