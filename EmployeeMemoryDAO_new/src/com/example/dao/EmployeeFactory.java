package com.example.dao;

public class EmployeeFactory {
    public EmployeeDAO createEmployeeDAO(){
        return new EmployeeDAOMemoryImpl();
    }
}
