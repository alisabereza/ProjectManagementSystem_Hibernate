package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domainDAO.CompanyDAO;

import java.time.LocalDate;

public class CompanyService {
    private final View view= null;
    private CompanyDAO companyDAO;

    public CompanyService(View view) {
          companyDAO = new CompanyDAO();
    }

    public CompanyService() {
        companyDAO = new CompanyDAO();
    }

    public void createCompany(Company company) {
        companyDAO.create(company);
    }

    public void deleteCompany() {
        view.write("Enter a Company name");
        String name = InputValueValidator.validateString(view);
       Company company = companyDAO.findByName(name);
       if (company==null) {view.write("Company not found");}
        else {companyDAO.delete(company);}
    }

    public Company readCompany(int id) {
        System.out.println("I am in CompanyService. ID is: " + id);
        Company company = companyDAO.read(id);
        if (company==null) {
            System.out.println("Company not found");}
        else {
            System.out.println("Company found:" + company);}
        return  company;
    }

    public Company findByName(String name) {
        return companyDAO.findByName(name);
    }
}
