package com.project.management.domainDAO;

import java.sql.SQLException;

public abstract class DataAccessObject <T>{

    public abstract void create(T t) throws SQLException;

    public abstract T read (int id) throws SQLException;

    public abstract void update (T t) throws SQLException;

    public abstract void delete (T t) throws SQLException;


}
