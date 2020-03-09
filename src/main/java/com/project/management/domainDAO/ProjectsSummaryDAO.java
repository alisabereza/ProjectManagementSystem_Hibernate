package com.project.management.domainDAO;

import com.project.management.database.DataBaseConnector;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProjectsSummaryDAO extends QueryDataAccessObject<Object> {
    private final static Logger LOG = LogManager.getLogger(ProjectsSummaryDAO.class);
    private HikariDataSource dataSource = DataBaseConnector.getConnector();

    //protected Connection connection;
    private static final String PROJ_SUMMARY = "select proj.date, proj.proj_name, proj.count from (select  distinct p.project_name as proj_name, max(p.start_date) as date, count(dp.developer_id) as count from projects p join dev_proj dp on p.id = dp.project_id group by p.project_name order by p.project_name) proj";


    @Override
    public void queryData(Object o) {

    }


    public void queryData() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(PROJ_SUMMARY)) {
            LOG.debug(String.format("Generating report: report.name=%s", PROJ_SUMMARY));
            ResultSet resultSet = statement.executeQuery();
            System.out.println("PROJECT STATISTICS");
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException | NullPointerException e) {
            LOG.error(String.format("Generating report: report.name=%s", PROJ_SUMMARY), e);
            System.out.println("Failed to execute query: " + e.getMessage());
        }
    }
}
