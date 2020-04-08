package com.project.management.domainDAO;


import com.project.management.database.HibernateDatabaseConnector;
import com.project.management.domain.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CompanyDAO extends DataAccessObject<Company> {
    private final static Logger LOG = LogManager.getLogger(CompanyDAO.class);
    private SessionFactory sessionFactory;

    public CompanyDAO() {
        sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }

    @Override
    public void create(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Creating company: %s", company.getName()));
        session.save(company);
        transaction.commit();
        session.close();
        LOG.debug(String.format("Company created: %s", company.getName()));
        System.out.println(String.format("Company created: %s", company.getName()));
    }

    @Override
    public void read(Company company) {

    }

    @Override
    public void update(Company company) {

    }

    @Override
    public void delete(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Deleting company: %s", company.getName()));
        session.delete(company);
        LOG.debug(String.format("Company deleted: %s", company.getName()));
        System.out.println(String.format("Company deleted: %s", company.getName()));
        transaction.commit();
        session.close();
    }

    public Company findByName(String name) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Finding company, name = %s", name));
        Query query2 = session.createQuery("from Company as c where c.name=:name");
        Company result;
        try {
        result = (Company) query2.setParameter("name", name).uniqueResult();
        transaction.commit();}
        catch (Exception e) {
            throw new NullPointerException("Company with the name provided does not exist in database");
        }
        session.close();
        return result;
    }
}
