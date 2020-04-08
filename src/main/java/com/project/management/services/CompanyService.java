package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domainDAO.CompanyDAO;

import java.time.LocalDate;

public class CompanyService {
    private final View view;
    private CompanyDAO companyDAO;

    public CompanyService(View view) {
        this.view = view;
        companyDAO = new CompanyDAO();
    }

    public void createCompany() {
        view.write("Enter a Company name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter Start Date in format YYYY-MM-DD");
        LocalDate startDate = InputValueValidator.validateDate(view);
        Company Company = new Company(name, startDate);
        companyDAO.create(Company);
    }

    public void deleteCompany() {
        view.write("Enter a Company name");
        String name = InputValueValidator.validateString(view);
       Company company = companyDAO.findByName(name);
       if (company==null) {view.write("Company not found");}
        else {companyDAO.delete(company);}
    }

    public void readCompany() {
        view.write("Enter id");
        int id = InputValueValidator.validateInt(view);
        Company company = companyDAO.read(id);
        if (company==null) {view.write("Company not found");}
        else {view.write("Company found:" + company.toString());}
    }
}
