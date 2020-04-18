package com.project.management.console;

import com.project.management.database.HibernateDatabaseConnector;
import com.project.management.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private View view;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public Controller(View view) {
        this.view = view;
        LOG.trace("Application started");
    }

    public void readOption() {
        view.write("Welcome to Project Management System!");
        view.write("For CRUD operations, type 'crud'");
        view.write("To Exit, type 'exit': ");
        while (true) {
            String read = view.read();
            readSubOption(read);
        }
    }

    public void readSubOption(String read) {
        switch (read) {
            case "crud": {
                view.write("Make your selection: 'create', 'read', 'update', 'delete'");

                String readCrud = view.read();
                readCrudOption(readCrud);
                break;
            }
            case "exit": {
                view.write("Goodbye!");
                HibernateDatabaseConnector.destroy();
                System.exit(0);
                break;
            }
            default: {
                view.write("Enter the correct command");
                break;
            }
        }
    }

    public void readCrudOption(String read) {
        switch (read) {
            case "create": {
                view.write("What do you want to create: 'company', 'customer', 'developer', 'project'");

                String readCreate = view.read();
                readCreateOption(readCreate);

                break;
            }

            case "read": {
                view.write("What do you want to find: 'company', 'customer', 'developer', 'project'");

                String readOption = view.read();
                readReadOption(readOption);

                break;
            }
            case "update": {
                view.write("What do you want to update: 'customer', 'developer'");

                String readUpdate = view.read();
                readUpdateOption(readUpdate);
                break;


            }
            case "delete": {
                view.write("What do you want to delete: 'company', 'customer', 'developer', 'project'");

                String readDelete = view.read();
                readDeleteOption(readDelete);
                break;

            }
            default: {
                view.write("Enter the correct command");
                readOption();
                break;
            }
        }
    }

    public void readCreateOption(String readCreate) {
        switch (readCreate) {
            case "company": {
                CompanyService service = new CompanyService(view);
               // service.createCompany();
                readOption();
                break;
            }
            case "customer": {
                CustomerService service = new CustomerService(view);
                service.createCustomer();
                readOption();
                break;
            }
            case "developer": {
                DeveloperService service = new DeveloperService(view);
               // service.createDeveloper();
                readOption();
                break;
            }
            case "project": {
                ProjectService service = new ProjectService(view);
                service.createProject();
                readOption();
                break;
            }
            default: {
                view.write("Enter the correct command");
                readOption();
                break;
            }
        }
    }

    private void readReadOption(String readOption) {
        switch (readOption) {
            case "company": {
                CompanyService service = new CompanyService(view);
                //service.readCompany();
                readOption();
                break;
            }
            case "customer": {
                CustomerService service = new CustomerService(view);
                service.readCustomer();
                readOption();
                break;
            }
            case "developer": {
                DeveloperService service = new DeveloperService(view);
                service.readDeveloper();
                readOption();
                break;
            }
            case "project": {
                ProjectService service = new ProjectService(view);
                service.readProject();
                readOption();
                break;
            }
            default: {
                view.write("Enter the correct command");
                readOption();
                break;
            }
        }
    }

    public void readUpdateOption(String readUpdate) {
        switch (readUpdate) {

            case "customer": {
                CustomerService service = new CustomerService(view);
                service.updateCustomer();
                readOption();
                break;
            }
            case "developer": {
                DeveloperService service = new DeveloperService(view);
                service.updateDeveloper();
                readOption();
                break;
            }
            default: {
                view.write("Enter the correct command");
                readOption();
                break;
            }
        }
    }

    public void readDeleteOption(String readDelete) {
        switch (readDelete) {
            case "company": {
                CompanyService service = new CompanyService(view);
                service.deleteCompany();
                readOption();
                break;
            }
            case "customer": {
                CustomerService service = new CustomerService(view);
                service.deleteCustomer();
                readOption();
                break;
            }
            case "developer": {
                DeveloperService service = new DeveloperService(view);
                service.deleteDeveloper();
                readOption();
                break;
            }
            case "project": {
                ProjectService service = new ProjectService(view);
                service.deleteProject();
                readOption();
                break;
            }
            default: {
                view.write("Enter the correct command");
                readOption();
                break;
            }
        }
    }
}
