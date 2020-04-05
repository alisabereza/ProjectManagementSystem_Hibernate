package com.project.management.domainDAO;

import com.project.management.database.HibernateDatabaseConnector;
import com.project.management.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

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
        NativeQuery query = session.createNativeQuery("select * from customers where customer_phone= '" + phone + "'");
        query.addEntity(Customer.class);
        Customer result = (Customer) query.getSingleResult();
        transaction.commit();
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
        session.close();
    }

    @Override
    public void read(Customer customer) {

    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Updating customer: customer.name=%s", customer.getName()));
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOG.debug(String.format("Deleting customer = %s", customer.getName()));
        session.delete(customer);
        transaction.commit();
        session.close();
    }
}