package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Customer;
import com.project.management.domainDAO.CustomerDAO;
import com.project.management.domainDAO.DataAccessObject;

import java.sql.SQLException;

public class CustomerService {
    private final View view;
    private DataAccessObject<Customer> customerDAO;

    public CustomerService(View view) {
        this.view = view;
        customerDAO = new CustomerDAO();
    }

    public void createCustomer() {
        view.write("Enter a customer name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter a customer phone");
        String phone = InputValueValidator.validateString(view);
        Customer customer = new Customer(name, phone);
        try {
            customerDAO.create(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer() {
        view.write("Enter a customer name");
        String name = InputValueValidator.validateString(view);
        try {
            customerDAO.delete(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer() {
        view.write("Enter a customer name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter new phone number");
        String phone = InputValueValidator.validateString(view);
        Customer customer = new Customer(name, phone);
        try {
            customerDAO.update(customer);
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }
}
