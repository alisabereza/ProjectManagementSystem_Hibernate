package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domain.Developer;
import com.project.management.domain.Project;
import com.project.management.domainDAO.CompanyDAO;
import com.project.management.domainDAO.ProjectDAO;

import java.time.LocalDate;

public class ProjectService {
    private final View view;
    private ProjectDAO projectDAO;

    public ProjectService(View view) {
        this.view = view;
        projectDAO = new ProjectDAO();
    }

    public void createProject() {
        view.write("Enter a Project name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter Start Date in format YYYY-MM-DD");
        LocalDate startDate = InputValueValidator.validateDate(view);
        view.write("Enter Project cost");
        int cost = InputValueValidator.validateInt(view);
        view.write("Enter Company name");
        String companyName = InputValueValidator.validateString(view);
        Company company;
        company = new CompanyDAO().findByName(companyName);
        if (company==null) {view.write("Company not found");}
        else {
        Project project = new Project(name, startDate, cost, company);
        projectDAO.create(project);}
    }
    public void readProject() {
        view.write("Enter id");
        int id = InputValueValidator.validateInt(view);
        Project project   = projectDAO.read(id);
        if (project==null) {view.write("Project not found");}
        else {view.write("Project found:" + project.toString());}
    }

    public void deleteProject() {
        view.write("Enter a Project name");
        String name = InputValueValidator.validateString(view);
        Project project = projectDAO.findByName(name);
        if (project==null) {view.write("Project not found");}
        else {projectDAO.delete(project);}
    }
}
