package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Customer;
import com.project.management.domainDAO.CustomerDAO;

public class CustomerService {
    private final View view;
    private CustomerDAO customerDAO;

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
        customerDAO.create(customer);
    }

    public void deleteCustomer() {
        view.write("Enter a customer phone");
        String phone = InputValueValidator.validateString(view);
        Customer customer = customerDAO.findByPhone(phone);
        customerDAO.delete(customer);

    }

    public void updateCustomer() {
        view.write("Enter phone number");
        String phone = InputValueValidator.validateString(view);
        Customer customer = customerDAO.findByPhone(phone);
        System.out.println("Enter new phone number: ");
        phone = InputValueValidator.validateString(view);
        customer.setPhone(phone);
        customerDAO.update(customer);

    }
}
