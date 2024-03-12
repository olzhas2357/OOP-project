package com.example.dao;

import com.example.model.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends AutoCloseable {
    void add(Employee emp) throws DAOException;
    void update(Employee emp) throws DAOException;
    void delete(int id) throws DAOException, SQLException;
    Employee findById(int id) throws DAOException, SQLException;
    Employee[] getAllEmployees() throws DAOException, SQLException;
    
}