package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersByLevelDAO extends QueryDataAccessObject<Integer> {
    private final static Logger LOG = LogManager.getLogger(DevelopersByLevelDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();
    private static final String DEV_LEVEL = "select level.developer_name from (select distinct devskill.developer_name, max(devskill.level) as level from (select d.id, d.developer_name, s.language, s.level, s.id from developers d\n" +
            "    join dev_skills ds on d.id = ds.developer_id\n" +
            "    join skills s on ds.skill_id = s.id) devskill\n" +
            "group by devskill.developer_name) level\n" +
            "where level.level = ?";


    @Override
    public void queryData(Integer level) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEV_LEVEL)) {
            LOG.debug(String.format("Generating report: report.name=%s", DEV_LEVEL));
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("MAX level per developer ");
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            LOG.error(String.format("Error generating report: report.name=%s", DEV_LEVEL), e);
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void queryData()  {

    }
}


