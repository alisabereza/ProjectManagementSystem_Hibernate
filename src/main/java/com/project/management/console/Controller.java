package com.project.management.console;

import com.project.management.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

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
        view.write("To get REPORT, type 'report'");
        view.write("To Exit, type 'exit': ");
        while (true) {
            String read = view.read();
            readSubOption(read);
        }
    }

    public void readSubOption(String read) {
        switch (read) {
            case "crud": {
                view.write("Make your selection: 'create', 'update', 'delete'");

                String readCrud = view.read();
                readCrudOption(readCrud);
                break;


            }
            case "report": {
                view.write("Make your selection: ");
                view.write("Salary of all developers of certain project - 'salary_proj'");
                view.write("All developers of certain project - 'dev_proj'");
                view.write("List of all developers using certain language - 'dev_lang'");
                view.write("List of all MIDDLE developers - 'middle_dev'");
                view.write("List of all projects - 'all_proj'");

                String readReport = view.read();
                readReportOption(readReport);

                break;
            }
            case "exit": {
                view.write("Goodbye!");
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
                view.write("What do you want to create: 'company', 'customer', 'developer'");

                String readCreate = view.read();
                readCreateOption(readCreate);

                break;
            }
            case "update": {
                view.write("What do you want to update: 'customer', 'developer'");

                String readUpdate = view.read();
                readUpdateOption(readUpdate);
                break;


            }
            case "delete": {
                view.write("What do you want to delete: 'company', 'customer', 'developer'");

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
                service.createCompany();
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
                service.createDeveloper();
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
            default: {
                view.write("Enter the correct command");
                readOption();
                break;
            }
        }
    }

    public void readReportOption(String readReport) {
        switch (readReport) {
            case "salary_proj": {
                ReportService service = new ReportService(view);
                service.salaryByProject();
                readOption();
                break;
            }
            case "dev_proj": {
                ReportService service = new ReportService(view);
                service.developersByProject();
                readOption();
            }
            case "dev_lang": {
                ReportService service = new ReportService(view);
                service.developersByLanguage();
                readOption();
            }
            case "middle_dev": {
                ReportService service = new ReportService(view);
                service.developersByLevel();
                readOption();
            }
            case "all_proj": {
                ReportService service = new ReportService(view);
                service.allProjects();
                readOption();
            }
            default: {
                view.write("Enter the correct command");
                readOption();
                break;
            }
        }
    }


}
