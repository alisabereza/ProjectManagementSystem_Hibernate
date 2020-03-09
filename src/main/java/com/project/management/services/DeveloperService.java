package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Developer;
import com.project.management.domainDAO.DeveloperDAO;
import com.project.management.domainDAO.DataAccessObject;
import com.project.management.domain.DeveloperGender;

import java.sql.SQLException;

public class DeveloperService {
    private final View view;
    private DataAccessObject<Developer> developerDAO;

    public DeveloperService(View view) {
        this.view = view;
        developerDAO = new DeveloperDAO();

    }

    public void createDeveloper() {
        view.write("Enter a Developer name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter a age");
        int age = InputValueValidator.validateInt(view);
        view.write("Enter a Developer gender ('MAN' or 'WOMAN')");
        String gender = InputValueValidator.validateGender(view);
        view.write("Enter a Developer salary");
        int salary = InputValueValidator.validateInt(view);
        Developer developer = new Developer(name, age, DeveloperGender.valueOf(gender), salary);
        try {
            developerDAO.create(developer);
        } catch (SQLException e) {
            System.out.println("Problem creating developer: " + e.getMessage());
        }
    }

    public void updateDeveloper() {
        view.write("Enter a Developer name");
        String name = InputValueValidator.validateString(view);
        view.write("Enter new salary");
        int salary = InputValueValidator.validateInt(view);
        Developer developer = new Developer(name, salary);
        try {
            developerDAO.update(developer);
        } catch (SQLException e) {
            System.out.println("Problem updating developer: " + e.getMessage());
        }
    }

    public void deleteDeveloper() {
        view.write("Enter a Developer name");
        String name = InputValueValidator.validateString(view);
        try {
            developerDAO.delete(name);
        } catch (SQLException e) {
            System.out.println("Problem deleting developer: " + e.getMessage());
        }
    }

}
