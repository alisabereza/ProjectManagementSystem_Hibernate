package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.project.management.domain.Project;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProjectDAO extends DataAccessObject <Project> {
    private final static Logger LOG = LogManager.getLogger(ProjectDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();

    private final static String INSERT = "INSERT INTO projects (project_name, start_date, company_id, cost) VALUES (?,?,(select id from companies where company_name = ?), ?);";


    @Override
    public  void create(Project project)  {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            LOG.debug(String.format("Creating project: project.name=%s", project.getName()));
            statement.setString(1, project.getName());
            statement.setDate(2, Date.valueOf(project.getStart_date()));
            statement.setString(3, project.getCompanyName());
            statement.setInt(4, project.getCost());
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Project  " + project.getName() + " created");
        } catch (SQLException e) {
            LOG.error(String.format("Error creating project: project.name=%s", project.getName()), e);
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void read(Project project)  {

    }

    @Override
    public void update(Project project)  {

    }

    @Override
    public void delete(String t)  {

    }


}

