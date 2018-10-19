package org.soul.web.classifier.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory factory;
	private static final ServiceRegistry svcRegistry;
	static {
		Configuration cfg = new Configuration();
		cfg.configure();
		svcRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		try{
			factory = cfg.buildSessionFactory(svcRegistry);
		}catch(Exception e){
			  System.err.println("Initial SessionFactory creation failed." + e);
	          throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
}
