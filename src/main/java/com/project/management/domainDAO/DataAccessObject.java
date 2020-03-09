package com.project.management.domainDAO;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DataAccessObject <T>{

    public abstract void create(T t) throws SQLException;

    public abstract void read (T t) throws SQLException;

    public abstract void update (T t) throws SQLException;

    public abstract void delete (String t) throws SQLException;
}
