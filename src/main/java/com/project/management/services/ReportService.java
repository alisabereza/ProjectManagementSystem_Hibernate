package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domainDAO.*;

import java.sql.SQLException;


public class ReportService {
    private final View view;
    private QueryDataAccessObject queryDataAccessObject;

    public ReportService(View view) {
        this.view = view;
    }

    public void salaryByProject() {
        queryDataAccessObject = new SalaryByProjectDAO();
        view.write("Enter a Project name");
        String name = InputValueValidator.validateString(view);
        try {
            queryDataAccessObject.queryData(name);
        } catch (SQLException e) {
            System.out.println("Problem getting the report: " + e.getMessage());
        }
    }

    public void developersByProject() {
        queryDataAccessObject = new DevelopersByProjectDAO();
        view.write("Enter a Project name");
        String name = InputValueValidator.validateString(view);

        try {
            queryDataAccessObject.queryData(name);
        } catch (SQLException e) {
            System.out.println("Problem getting the report: " + e.getMessage());
        }
    }

    public void developersByLevel() {
        queryDataAccessObject = new DevelopersByLevelDAO();
        view.write("Select level: 1, 2 or 3");
        int level = InputValueValidator.validateInt(view);
        try {
            queryDataAccessObject.queryData(level);
        } catch (SQLException e) {
            System.out.println("Problem getting the report: " + e.getMessage());
        }
    }

    public void developersByLanguage() {
        queryDataAccessObject = new DevelopersByLanguageDAO();
        view.write("Enter Programming Language");
        String language = InputValueValidator.validateString(view);

        try {
            queryDataAccessObject.queryData(language);
        } catch (SQLException e) {
            System.out.println("Problem getting the report: " + e.getMessage());
        }
    }

    public void allProjects() {
        queryDataAccessObject = new ProjectsSummaryDAO();
        System.out.println(queryDataAccessObject.toString());
        try {
            queryDataAccessObject.queryData();
        } catch (SQLException e) {
            System.out.println("Problem getting the report: " + e.getMessage());
        }
    }


}