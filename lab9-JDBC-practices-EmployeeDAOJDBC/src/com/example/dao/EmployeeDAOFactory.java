package com.example.dao;

import java.sql.SQLException;

public class EmployeeDAOFactory {

    public EmployeeDAO createEmployeeDAO() throws SQLException {
        return new EmployeeDAOJDBCImpl();
    }
}