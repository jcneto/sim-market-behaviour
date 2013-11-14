package app.persistence.dao;

import java.util.List;

import app.model.Prateleira;
import app.persistence.HibernateUtil;

public class PrateleiraDAOImpl extends GenericDAOImpl<Prateleira, Integer> {

	@Override
	public Prateleira load(Integer pk) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Prateleira load = (Prateleira) session.get(Prateleira.class, pk);
		session.getTransaction().commit();
		return load;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prateleira> getList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Prateleira> prats = session.createQuery("from Prateleira").list();
		session.getTransaction().commit();
		return prats;
	}
}
