package app.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import app.model.Cliente;
import app.model.Prateleira;
import app.model.Produto;
import app.model.TipoProduto;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new AnnotationConfiguration().setProperty(
					"hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
					.setProperty("hibernate.connection.driver_class",
							"com.mysql.jdbc.Driver").setProperty(
							"hibernate.connection.url",
							"jdbc:mysql://localhost:3306/mercadoUbiquo")
					.setProperty("hibernate.connection.username", "root")
					.setProperty("hibernate.connection.password", "root")
					.setProperty("hibernate.connection.pool_size", "10")
					// .setProperty("hibernate.show_sql", "true")
					.setProperty("hibernate.current_session_context_class",
							"thread")
				// .setProperty("hibernate.hbm2ddl.auto","create")
					.addAnnotatedClass(Cliente.class).addAnnotatedClass(
							Prateleira.class).addAnnotatedClass(Produto.class)
					.addAnnotatedClass(TipoProduto.class).buildSessionFactory();
		} catch (Throwable ex) {

			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}