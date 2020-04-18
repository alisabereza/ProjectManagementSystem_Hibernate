package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domain.Customer;
import com.project.management.domain.Developer;
import com.project.management.domainDAO.CompanyDAO;
import com.project.management.domainDAO.DeveloperDAO;
import com.project.management.domain.DeveloperGender;

public class DeveloperService {
    private final View view=null;
    private DeveloperDAO developerDAO;

    public DeveloperService(View view) {
        //this.view = view;
        developerDAO = new DeveloperDAO();

    }

    public DeveloperService() {
        developerDAO = new DeveloperDAO();
    }

    public void createDeveloper(Developer developer) {
        developerDAO.create(developer);
    }

    public void readDeveloper() {
        view.write("Enter id");
        int id = InputValueValidator.validateInt(view);
        Developer developer  = developerDAO.read(id);
        if (developer==null) {view.write("Developer not found");}
        else {view.write("Developer found:" + developer.toString());}
    }

    public void updateDeveloper() {
        view.write("Enter a Developer name");
        String name = InputValueValidator.validateString(view);
        try {
            Developer developer = developerDAO.findByName(name);
            view.write("Select what to update: 'salary' or 'company': ");
            String answer = view.read();
            switch (answer) {
                case "salary": {
                    view.write("Enter new Salary: ");
                    int newSalary = InputValueValidator.validateInt(view);
                    developer.setSalary(newSalary);
                    break;
                }
                case "company": {
                    view.write("Enter new Company name: ");
                    String newCompanyName = InputValueValidator.validateString(view);
                    Company company = new CompanyDAO().findByName(newCompanyName);
                    if (company != null) {
                        developer.setCompany(company);
                    } else {
                        view.write("Company not found");
                    }
                    break;
                }
                default:
                    view.write("Incorrect command");
            }
            developerDAO.update(developer);
        }
        catch (NullPointerException e) {
            view.write(e.getMessage());
        }
    }

    public void deleteDeveloper() {
        view.write("Enter a Developer name");
        String name = InputValueValidator.validateString(view);
        Developer developer = developerDAO.findByName(name);
        if (developer==null) {view.write("Developer not found");}
        else {developerDAO.delete(developer);}
    }

    public Developer findByName(String name) {
        return developerDAO.findByName(name);
    }
}
