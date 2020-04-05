package com.project.management.domainDAO;

import com.project.management.database.HibernateDatabaseConnector;
import com.project.management.domain.Developer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.Serializable;


public class DeveloperDAO extends DataAccessObject<Developer> {
    private final static Logger LOG = LogManager.getLogger(DeveloperDAO.class);
    private SessionFactory sessionFactory;

    public DeveloperDAO() {
        sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }


    @Override
    public void create(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Creating developer: %s", developer.getName()));
        session.save(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public void read(Developer developer) {

    }

    @Override
    public void update(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Updating developer: developer.name=%s", developer.getName()));
        session.update(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Deleting developer: %s", developer.getName()));
        Serializable id = developer.getId();
        Object persistentInstance = session.load(Developer.class, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            LOG.debug(String.format("Developer deleted: %s", developer.getName()));
            transaction.commit();
        } else {
            LOG.error(String.format("Developer was not deleted: %s", developer.getName()));
        }


        session.close();
    }

    public Developer findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Finding Developer, name = %s", name));
        NativeQuery query = session.createNativeQuery("select * from developers where developer_name= '" + name + "'");
        query.addEntity(Developer.class);
        Developer result = (Developer) query.getSingleResult();
        transaction.commit();
        session.close();
        return result;
    }
}
