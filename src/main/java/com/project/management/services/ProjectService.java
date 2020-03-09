package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domain.Project;

import com.project.management.domainDAO.DataAccessObject;
import com.project.management.domainDAO.ProjectDAO;


import java.sql.SQLException;
import java.time.LocalDate;

public class ProjectService {
    private final View view;
    private DataAccessObject<Project> projectDAO;

    public ProjectService(View view) {
        this.view = view;
        projectDAO = new ProjectDAO();

    }

    public void createProject() throws SQLException {
        view.write("Enter a Project name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter Start Date in format YYYY-MM-DD");
        LocalDate startDate = InputValueValidator.validateDate(view);
        view.write("Enter Project cost");
        int cost = InputValueValidator.validateInt(view);
        view.write("Enter Company name");
        String companyName = InputValueValidator.validateString(view);
        Project Project = new Project(name, startDate, cost, companyName);
        projectDAO.create(Project);
    }
}
