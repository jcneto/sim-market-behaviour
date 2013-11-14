package app.persistence.dao;

import java.util.List;

import app.model.Cliente;
import app.persistence.HibernateUtil;

public class ClienteDAOImpl extends GenericDAOImpl<Cliente, Integer> {

	@Override
	public Cliente load(Integer pk) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Cliente load = (Cliente) session.get(Cliente.class, pk);
		session.getTransaction().commit();
		return load;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> getList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Cliente> clientes = session.createQuery("from Cliente").list();
		session.getTransaction().commit();
		return clientes;
	}

}
