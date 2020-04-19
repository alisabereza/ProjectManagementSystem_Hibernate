package com.project.management.model.customer;

import com.project.management.database.HibernateDatabaseConnector;
import com.project.management.model.common.DataAccessObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CustomerDAO extends DataAccessObject<Customer> {
    private final static Logger LOG = LogManager.getLogger(CustomerDAO.class);
    private SessionFactory sessionFactory;

    public CustomerDAO() {
        sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }

    public Customer findByPhone(String phone) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Finding customer, phone = %s", phone));
        Query query = session.createQuery("from Customer c where c.phone = :phone");

        Customer result;
        try { result = (Customer) query.setParameter("phone", phone).uniqueResult();
            transaction.commit();}
        catch (Exception e) {
            throw new NullPointerException("Customer with the phone number provided does not exist in database");
        }
        session.close();
        return result;
    }


    @Override
    public void create(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Creating customer: %s", customer.getName()));
        session.save(customer);
        transaction.commit();
        LOG.debug(String.format("Customer created: %s", customer.getName()));
        System.out.println(String.format("Customer created: %s", customer.getName()));
        session.close();
    }

    @Override
    public Customer read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Finding Customer by id: %s", id));
        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        session.close();
        LOG.debug(String.format("Customer: %s", customer));
        return customer;
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Updating customer: customer.name=%s", customer.getName()));
        session.update(customer);
        transaction.commit();
        LOG.debug(String.format("Customer update: %s", customer.getName()));
        System.out.println(String.format("Customer updated: %s", customer.getName()));
        session.close();
    }

    @Override
    public void delete(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Deleting customer = %s", customer.getName()));
        session.delete(customer);
        transaction.commit();
        LOG.debug(String.format("Customer deleted: %s", customer.getName()));
        System.out.println(String.format("Customer deleted: %s", customer.getName()));
        session.close();
    }
}