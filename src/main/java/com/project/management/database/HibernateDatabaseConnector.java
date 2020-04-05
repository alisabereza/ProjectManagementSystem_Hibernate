package com.project.management.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateDatabaseConnector {
    private static final Logger LOG = LogManager.getLogger(HibernateDatabaseConnector.class);
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private static HibernateDatabaseConnector instance = new HibernateDatabaseConnector();

    private HibernateDatabaseConnector() {
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources sources = new MetadataSources(registry);
            final Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            LOG.error("init hibernate", e);
        }
    }

    public static HibernateDatabaseConnector getInstance() {
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static synchronized void init() {
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources sources = new MetadataSources(registry);
            final Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            LOG.error("init hibernate", e);
        }
    }

    public static synchronized void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
