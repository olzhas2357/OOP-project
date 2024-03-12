package com.example.dao;

import com.example.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOMemoryImpl implements EmployeeDAO{
    private static final Employee[] employeeArray = new Employee[10];
    EmployeeDAOMemoryImpl(){

    }
    @Override
    public void add(Employee emp) {
        employeeArray[emp.getId()] = emp;
    }

    @Override
    public void update(Employee emp) {
        employeeArray[emp.getId()] = emp;
    }

    @Override
    public void delete(int id) {
        employeeArray[id] = null;
    }

    @Override
    public Employee findById(int id) {
        return employeeArray[id];
    }

    @Override
    public Employee[] getAllEmployees() {
        List<Employee> emps = new ArrayList<>();
        for(Employee e : employeeArray){
            if(e != null){
                emps.add(e);
            }
        }
        return emps.toArray(new Employee[0]);
    }
}
