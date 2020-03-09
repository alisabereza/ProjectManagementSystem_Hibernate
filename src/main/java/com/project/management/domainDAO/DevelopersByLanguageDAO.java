package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersByLanguageDAO extends QueryDataAccessObject <String> {
    private final static Logger LOG = LogManager.getLogger(DevelopersByLanguageDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();

    private static final String DEVELOPERS_BY_LANGUAGE = "select distinct developer_name, d.developer_age, d.developer_gender, d.salary\n" +
            "from developers d\n" +
            "         join dev_skills ds on d.id = ds.developer_id\n" +
            "where ds.skill_id in (select id from skills where upper(language) = upper(?));";


    @Override
    public void queryData(String language)  {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEVELOPERS_BY_LANGUAGE)) {
            LOG.debug(String.format("Generating report: report.name=%s", DEVELOPERS_BY_LANGUAGE));
            statement.setString(1, language);
            ResultSet resultSet = statement.executeQuery();
            System.out.println ("The following developers use " + language + ": ");
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next())
            {
                for (int i=1; i <= columns; i++) {
                    System.out.print(resultSet.getString(i) + "  ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            LOG.error(String.format("Error generating report: report.name=%s", DEVELOPERS_BY_LANGUAGE), e);
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void queryData()  {

    }
}

