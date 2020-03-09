package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersByProjectDAO extends QueryDataAccessObject <String> {
    private final static Logger LOG = LogManager.getLogger(DevelopersByProjectDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();

    private static final String DEVELOPERS_BY_PROJECT = "select developer_name from developers d join dev_proj dp on d.id = dp.developer_id where dp.project_id in (select id from projects where project_name = ?);";


    @Override
    public void queryData(String name)  {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEVELOPERS_BY_PROJECT)) {
            LOG.debug(String.format("Generating report: report.name=%s", DEVELOPERS_BY_PROJECT));
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            System.out.println ("The following developers work in " + name + " project: ");
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next())
            {
                for (int i=1; i <= columns; i++) {
                    System.out.println(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            LOG.error(String.format("Generating report: report.name=%s", DEVELOPERS_BY_PROJECT), e);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void queryData()  {

    }
}
