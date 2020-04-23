package com.project.management.controller;

import com.project.management.model.customer.Customer;
import com.project.management.model.customer.CustomerDAO;
import com.project.management.utils.EntityValidator;
import com.project.management.utils.ErrorMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/customer/*")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if (action.startsWith("/findCustomer")) {
            req.getRequestDispatcher("/view/customer/find_customer.jsp").forward(req, resp);

        }  if (action.startsWith("/createCustomer")) {
           req.getRequestDispatcher("/view/customer/create_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateCustomer")) {
            req.getRequestDispatcher("/view/customer/update_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteCustomer")) {
            req.getRequestDispatcher("/view/customer/delete_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/allCustomers")) {
            List<Customer> customers = customerDAO.getAll();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/view/customer/all_customers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if (action.startsWith("/createCustomer")) {
            Customer customer = mapCustomer(req);
            List<ErrorMessage> errorMessages = validateCustomer(customer);
            if (!errorMessages.isEmpty()) {
                req.setAttribute("errors", errorMessages);
            } else {
                customerDAO.create(customer);
                req.setAttribute("message", "Customer created: " + customer.getName());
            }
            req.getRequestDispatcher("/view/customer/create_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateCustomer")) {
            Customer customer = customerDAO.read(Integer.parseInt(req.getParameter("id")));
            if (customer==null)
            {
                req.setAttribute("message", "Customer not found");
            }
            else {
                String newPhone = req.getParameter("phone");
                customer.setPhone(newPhone);
                customerDAO.update(customer);
                req.setAttribute("message", String.format("Customer updated: ID=%s, name=%s, phone=%s",customer.getId(), customer.getName(), customer.getPhone()));
            }
            req.getRequestDispatcher("/view/customer/update_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteCustomer")) {
            int id = Integer.parseInt(req.getParameter("id"));
            if (customerDAO.read(id)==null)

            {
                req.setAttribute("message", "Customer not found");
            }
            else {customerDAO.delete(customerDAO.read(id));
                req.setAttribute("message", String.format("Customer with ID=%s deleted", id));}
            req.getRequestDispatcher("/view/customer/delete_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/findCustomer")) {
            final String id = req.getParameter("id").trim();
            final Customer customer = customerDAO.read(Integer.parseInt(id));
            if (customer==null)
            {
                req.setAttribute("message", "Customer not found");
            }
            else {
                req.setAttribute("message", String.format("Customer found: ID=%s, name=%s, phone=%s",customer.getId(), customer.getName(), customer.getPhone()));
            }
            req.getRequestDispatcher("/view/customer/find_customer.jsp").forward(req,resp);
        }

    }

    private Customer mapCustomer(HttpServletRequest req){
        final String name = req.getParameter("name").trim();
        final String phone = req.getParameter("phone");

        Customer customer = new CustomerDAO().findByName(req.getParameter("companyName"));
        return new Customer(name, phone);
    }

    private List<ErrorMessage> validateCustomer (Customer customer){
        final List<ErrorMessage> errorMessages = EntityValidator.validateEntity(customer);
        final Customer persistentCustomer = customerDAO.findByName(customer.getName());
        if (Objects.nonNull(persistentCustomer) && !persistentCustomer.getName().isEmpty()) {
            errorMessages.add(new ErrorMessage("", "Customer with this name already exists"));
        }
        return errorMessages;
    }

    private String getAction (HttpServletRequest req){
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}

