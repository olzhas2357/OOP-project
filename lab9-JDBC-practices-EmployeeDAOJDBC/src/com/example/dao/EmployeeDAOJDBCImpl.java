package com.example.dao;

import com.example.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAOJDBCImpl implements EmployeeDAO{
//    private Connection con  = null;
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String username = "postgres";
    String password = "kbtu2023";
    private final Connection con = DriverManager.getConnection(url, username, password);

    public EmployeeDAOJDBCImpl() throws SQLException {
    }

//    try {
//        con = DriverManager.getConnection(url, username, password);
//    } catch (SQLException ex) {
//        throw new RuntimeException(ex);
//    }
    @Override
    public void add(Employee emp) throws DAOException {
        try(Statement stmt = con.createStatement()){
            String query = "INSERT INTO EMPLOYEE VALUES(" + emp.getId()
                    + ", '" + emp.getFirstName() + "', "
                    +"'" + emp.getLastName() + "', "
                    +"'" +
                    new java.sql.Date(emp.getBirthDate().getTime()) + "', "
                    + emp.getSalary() + ")";
            if(stmt.executeUpdate(query)!=1){
                throw new DAOException("Error adding employee");
            }
        } catch (SQLException e) {
            throw new DAOException("Error adding employee in DAO" + e);
        }
    }

    @Override
    public void update(Employee emp) throws DAOException {
        try (Statement statement = con.createStatement()){
            String query = "UPDATE EMPLOYEE "
                    + "SET FIRSTNAME=?, LASTNAME=?, BIRTHDATE=?, SALARY=? "
                    + "WHERE ID=?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(emp.getBirthDate().getTime()));
            preparedStatement.setDouble(4, emp.getSalary());
            preparedStatement.setInt(5, emp.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated != 1){
                throw new DAOException("Error updating employee");
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating employee in DAO", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException, SQLException {
        Employee emp = findById(id);
        if (emp == null){
            throw new DAOException("Employee id: " + id + " does not exist to delete.");
        }
        try(Statement stmt = con.createStatement()){
            String query = "DELETE FROM EMPLOYEE WHERE ID=" + id;
            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected != 1) {
                throw new DAOException("Error deleting employee. Rows affected: " + rowsAffected);
            }
        }
        catch (SQLException e){
            throw new DAOException("Error deleting employee in DAO", e);
        }
    }

    @Override
    public Employee findById(int id) throws DAOException, SQLException {
        try (Statement stmt = con.createStatement()) {
            String select = "SELECT * FROM EMPLOYEE WHERE ID=" + id;
            ResultSet rs = stmt.executeQuery(select);
            if (!rs.next()) {
                return null;
            }
            return (new Employee(rs.getInt("ID"),
                    rs.getString("FIRSTNAME"),
                    rs.getString("LASTNAME"),
                    rs.getDate("BIRTHDATE"),
                    rs.getFloat("SALARY")));
        }
        catch (SQLException e){
            throw new DAOException("Error finding employee in DAO", e);
        }
    }
    @Override
    public Employee[] getAllEmployees() throws DAOException {
        try (Statement stmt = con.createStatement()){
            String query = "SELECT * FROM EMPLOYEE";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Employee> employees = new ArrayList<>();
            while (rs.next()){
                employees.add(new Employee(rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getDate("BIRTHDATE"),
                        rs.getFloat("SALARY")));
            }
            return employees.toArray(new Employee[0]);
        }
        catch (SQLException e){
            throw new DAOException("Error getting all employees in DAO", e);
        }
    }
    @Override
    public void close() throws Exception {
        try{
            con.close();
        }
        catch (SQLException e){
            System.out.println("Exception closing Connection: " + e);
        }
    }
}
