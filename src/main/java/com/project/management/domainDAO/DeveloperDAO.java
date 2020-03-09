package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.project.management.domain.Developer;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeveloperDAO extends DataAccessObject<Developer> {
    private final static Logger LOG = LogManager.getLogger(DeveloperDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();

    private final static String INSERT = "INSERT INTO developers (developer_name, developer_age, developer_gender, salary) VALUES (?,?,?,?);";
    private final static String DELETE_DEV_PROJ = "DELETE FROM dev_proj WHERE developer_id = (SELECT id FROM developers WHERE developer_name = ?);";
    private final static String DELETE_DEV_SKILL = "DELETE FROM dev_skills WHERE developer_id = (SELECT id FROM developers WHERE developer_name = ?);";
    private final static String DELETE = "DELETE FROM developers WHERE developer_name = ?;";
    private final static String UPDATE = "UPDATE developers set salary=? where developer_name = ?;";


    @Override
    public void create(Developer developer) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            LOG.debug(String.format("Creating developer: developer.name=%s", developer.getName()));
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setString(3, developer.getGender());
            statement.setInt(4, developer.getSalary());
            statement.execute();
            System.out.println("Developer " + developer.getName() + " created.");


        } catch (SQLException e) {
            LOG.error(String.format("Error creating developer: developer.name=%s", developer.getName()), e);
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void read(Developer developer) {

    }

    @Override
    public void update(Developer developer) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            LOG.debug(String.format("Updating developer: developer.name=%s", developer.getName()));
            statement.setString(2, developer.getName());
            statement.setInt(1, developer.getSalary());
            statement.execute();
            System.out.println("Developer " + developer.getName() + " updated.");


        } catch (SQLException e) {
            LOG.error(String.format("Error updating developer: developer.name=%s", developer.getName()), e);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DEV_PROJ)) {
            LOG.debug(String.format("Deleting developer-project dependency: developer.name=%s", name));
            statement.setString(1, name);
            statement.execute();
            System.out.println("Developer-project dependencies deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting developer-project dependency: developer.name=%s", name), e);
            System.out.println(e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DEV_SKILL)) {
            LOG.debug(String.format("Deleting developer-skill dependency: developer.name=%s", name));
            statement.setString(1, name);
            statement.execute();
            System.out.println("Developer-skills dependencies deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting developer-skill: developer.name=%s", name), e);
            System.out.println(e.getMessage());
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            LOG.debug(String.format("Deleting developer: developer.name=%s", name));
            statement.setString(1, name);
            statement.execute();
            System.out.println("Developer deleted");
        } catch (SQLException e) {
            LOG.error(String.format("Error deleting developer: developer.name=%s", name), e);
            System.out.println(e.getMessage());
        }

    }

}
