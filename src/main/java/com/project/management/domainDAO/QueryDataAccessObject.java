package com.project.management.domainDAO;

import java.sql.SQLException;

public abstract class QueryDataAccessObject <T> {
    public abstract void queryData(T t) throws SQLException;
    public abstract void queryData() throws SQLException;

}
