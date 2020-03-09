package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.project.management.domain.Customer;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO extends DataAccessObject<Customer> {
    private final static Logger LOG = LogManager.getLogger(CustomerDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();
    private final static String INSERT = "INSERT INTO customers (customer_name, customer_phone) " +
            "VALUES(?, ?);";

    private final static String DELETE_CUST_PROJ = "DELETE FROM cust_proj WHERE customer_id=(SELECT id FROM customers WHERE customer_name=?);";
    private final static String DELETE = "DELETE FROM customers WHERE customer_name=?;";

    private final static String UPDATE = "UPDATE customers set customer_phone=? where customer_name = ?;";

    @Override
    public void create(Customer customer) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            LOG.debug(String.format("Creating customer: customer.name=%s", customer.getName()));
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());
            statement.execute();
            System.out.println("Customer " + customer.toString() + " created.");
        } catch (SQLException e) {
            LOG.error(String.format("Error creating customer: customer.name=%s", customer.getName()), e);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void read(Customer customer) {

    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            LOG.debug(String.format("Updating customer: customer.name=%s", customer.getName()));
            statement.setString(1, customer.getPhone());
            statement.setString(2, customer.getName());
            statement.execute();
            System.out.println("Customer " + customer.toString() + " updated.");
        } catch (SQLException e) {
            LOG.error(String.format("Error updating customer: customer.name=%s", customer.getName()), e);
            System.out.println("Error in DAO: " + e.getMessage());
        }

    }

    @Override
    public void delete(String customer) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUST_PROJ)) {
            LOG.debug(String.format("Deleting customer-projects dependency: customer.name=%s", customer));
            System.out.println("Trying to delete customer projects: " + customer);
            statement.setString(1, customer);
            statement.execute();
            System.out.println("Customer projects deleted.");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting customer-projects dependency: customer.name=%s", customer), e);
            System.out.println("Could not delete customer project: " + e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            LOG.debug(String.format("Deleting customer: customer.name=%s", customer));
            System.out.println("Trying to delete customer: " + customer);
            statement.setString(1, customer);
            statement.execute();
            System.out.println("Customer " + customer + " deleted.");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting customer: customer.name=%s", customer), e);
            System.out.println("Could not delete customer: " + e.getMessage());
        }
    }
}