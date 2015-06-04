package com.timostaudinger.dailydose.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

class Database {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        SessionFactory factory = sessionFactory;
        if (factory instanceof SessionFactoryImpl) {
            SessionFactoryImpl sf = (SessionFactoryImpl) factory;
            ConnectionProvider conn = sf.getConnectionProvider();
            if (conn instanceof C3P0ConnectionProvider) {
                ((C3P0ConnectionProvider) conn).close();
            }
        }
        factory.close();
    }
}
