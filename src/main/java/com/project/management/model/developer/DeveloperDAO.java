package com.project.management.model.developer;

import com.project.management.database.HibernateDatabaseConnector;
import com.project.management.model.common.DataAccessObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
        LOG.debug(String.format("Developer created: %s", developer.getName()));
        session.close();
    }

    @Override
    public Developer read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Finding Developer by id: %s", id));
        Developer developer = session.get(Developer.class, id);
        transaction.commit();
        session.close();
        LOG.debug(String.format("Developer: %s", developer));
        return developer;
    }

    @Override
    public void update(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Updating developer: developer.name=%s", developer.getName()));
        session.update(developer);
        transaction.commit();
        LOG.debug(String.format("Developer updated: %s", developer.getName()));
        session.close();
    }

    @Override
    public void delete(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Deleting developer: %s", developer.getName()));
        session.delete(developer);
        transaction.commit();
        LOG.debug(String.format("Developer deleted: %s", developer.getName()));
        session.close();
    }

    public Developer findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Finding Developer, name = %s", name));
        Query query = session.createQuery("from Developer d where d.name= :name");
        Developer result= null;
        try {
            result = (Developer) query.setParameter("name", name).uniqueResult();
            transaction.commit();

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        session.close();
        return result;
    }

    public List<Developer> getAll() {
        LOG.debug("Generating All developers list");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Developer").getResultList();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }
}
