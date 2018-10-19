package org.soul.web.classifier.dbtest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.soul.web.classifier.db.HibernateUtil;
import org.soul.web.classifier.db.bean.WebPageDetails;

public class DBConnectionTest {

	public static void main(String args[]){
		System.setProperty("query_cache_size", "8096");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		WebPageDetails w  = new WebPageDetails();
		w.setUrl("http://ww.google.com");
		w.setContent("Content");
		
		session.save(w);
		session.getTransaction().commit();
		System.out.println("Data Inserted");
		session.close();
	}
}
