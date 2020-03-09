package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domainDAO.CompanyDAO;
import com.project.management.domainDAO.DataAccessObject;

import java.sql.SQLException;
import java.time.LocalDate;

public class CompanyService {
    private final View view;
    private DataAccessObject<Company> CompanyDAO;

    public CompanyService(View view) {
        this.view = view;
        CompanyDAO = new CompanyDAO();
    }

    public void createCompany() {
        view.write("Enter a Company name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter Start Date in format YYYY-MM-DD");
        LocalDate startDate = InputValueValidator.validateDate(view);
        Company Company = new Company(name, startDate);
        try {
            CompanyDAO.create(Company);
        } catch (SQLException e) {
            System.out.println("Problem creating new company: " + e.getMessage());
        }
    }

    public void deleteCompany() {
        view.write("Enter a Company name");
        String name = InputValueValidator.validateString(view);
        try {
            CompanyDAO.delete(name);
        } catch (SQLException e) {
            System.out.println("Problem deleting the company: " + e.getMessage());
        }
    }

}
