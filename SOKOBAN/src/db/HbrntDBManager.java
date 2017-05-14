package db;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sun.corba.se.impl.naming.pcosnaming.NameServer;

public class HbrntDBManager implements DBManager {

	private SessionFactory factory;

	@Override
	public List<DbObject> getTable(String s) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<DbObject> DBdata = new LinkedList<>();
		//////////// s=select * from users
		tx = session.beginTransaction();
		List fromDB = session.createQuery(s).list();
		for (Iterator iterator = fromDB.iterator(); iterator.hasNext();) {

			DbObject db = (DbObject) iterator.next();
			DBdata.add(db);

			// System.out.print(" Name: " + player.getUsername());
			// System.out.println(" ID: " + employee.getId());
		}

		return DBdata;
	}

	public HbrntDBManager() {

		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();

	}

	public void addUser(User u) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void addLevel(LevelInfo l) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(l);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void addUserData(UserData ud) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			// UserData ud2 = new UserData(ud);
			session.save(ud);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

}