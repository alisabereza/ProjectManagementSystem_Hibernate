package com.project.management.domainDAO;

import com.project.management.database.HibernateDatabaseConnector;
import com.project.management.domain.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.NoResultException;

public class ProjectDAO extends DataAccessObject<Project> {
    private final static Logger LOG = LogManager.getLogger(CustomerDAO.class);
    private SessionFactory sessionFactory;

    public ProjectDAO() {
        sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }

    @Override
    public void create(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Creating project: %s", project.getName()));
        session.save(project);
        transaction.commit();
        LOG.debug(String.format("Project created: %s", project.getName()));
        System.out.println(String.format("Project created: %s", project.getName()));
        session.close();
    }

    @Override
    public void read(Project project) {

    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void delete(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Deleting project: %s", project.getName()));
        session.delete(project);
        transaction.commit();
        LOG.debug(String.format("Project deleted: %s", project.getName()));
        System.out.println(String.format("Project deleted: %s", project.getName()));
        session.close();
    }

    public Project findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Finding Project, name = %s", name));
        NativeQuery query = session.createNativeQuery("select * from projects where project_name= '" + name + "'");
        query.addEntity(Project.class);
        Project result;
        try {result = (Project) query.getSingleResult();
        transaction.commit();}
        catch (NoResultException e) {
            throw new NullPointerException("Project with the phone number provided does not exist in database");
        }
        session.close();
        return result;
    }

}

