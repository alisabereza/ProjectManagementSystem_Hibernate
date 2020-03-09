package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.project.management.domain.Company;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyDAO extends DataAccessObject<Company> {
    private final static Logger LOG = LogManager.getLogger(CompanyDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();

    private final static String INSERT = "INSERT INTO companies (company_name, start_date) " +
            "VALUES(?, ?);";

    private final static String DELETE_DEV_PROJ = "DELETE FROM dev_proj where developer_id = (select id from developers where company_id = (select id from companies where company_name = ?))";
    private final static String DELETE_DEV_SKILLS = "DELETE FROM dev_skills where developer_id = (select id from developers where company_id = (select id from companies where company_name = ?))";
    private final static String DELETE_CUST_PROJ = "DELETE FROM cust_proj where project_id = (select id from projects where company_id = (select id from companies where company_name = ?))";
    private final static String DELETE_PROJ = "DELETE FROM projects where company_id = (select id from companies where company_name = ?)";
    private final static String DELETE_DEV = "DELETE FROM developers where company_id = (select id from companies where company_name = ?)";
    private final static String DELETE_COMPANY = "DELETE FROM companies where company_name = ?";


    @Override
    public void create(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            LOG.debug(String.format("Creating company: company.title=%s", company.getName()));
            statement.setString(1, company.getName());
            statement.setDate(2, Date.valueOf(company.getStart_date()));
            statement.execute();
            System.out.println("Company " + company.toString() + " created");
        } catch (SQLException e) {
            LOG.error(String.format("Error creating company: company.title=%s", company.getName()), e);
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void read(Company company)  {

    }

    @Override
    public void update(Company company)  {

    }

    @Override
    public void delete(String company)  {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_DEV_PROJ)) {

            LOG.debug(String.format("Deleting company developer-project dependency: company.title=%s", company));
            statement.setString(1, company);
            statement.execute();
            System.out.println("Developer-project dependencies deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting company developer-project dependency: company.title=%s", company), e);
            System.out.println(e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();

             PreparedStatement statement = connection.prepareStatement(DELETE_DEV_SKILLS)) {
            LOG.debug(String.format("Deleting company developer-skill dependency: company.title=%s", company));
            statement.setString(1, company);
            statement.execute();
            System.out.println("Developer-skills dependencies deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting company developer-project dependency: company.title=%s", company), e);
            System.out.println(e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUST_PROJ)) {
            LOG.debug(String.format("Deleting company customer-project dependency: company.title=%s", company));
            statement.setString(1, company);
            statement.execute();
            System.out.println("Customer-project dependencies deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting company customer-project dependency: company.title=%s", company), e);
            System.out.println(e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PROJ)) {
            LOG.debug(String.format("Deleting company projects: company.title=%s", company));
            statement.setString(1, company);
            statement.execute();
            System.out.println("Company developers deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting company projects: company.title=%s", company), e);
            System.out.println(e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DEV)) {
            LOG.debug(String.format("Deleting company developers: company.title=%s", company));
            statement.setString(1, company);
            statement.execute();
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting company developers: company.title=%s", company), e);
            System.out.println(e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COMPANY)) {
            LOG.debug(String.format("Deleting company: company.title=%s", company));
            statement.setString(1, company);
            statement.execute();
            System.out.println("Company " + company + " deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting company: company.title=%s", company), e);
            System.out.println(e.getMessage());
        }
    }
}
