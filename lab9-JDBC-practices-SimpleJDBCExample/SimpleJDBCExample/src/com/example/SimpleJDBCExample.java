package com.example;

import java.sql.*;
import java.util.Date;

public class SimpleJDBCExample {

    public static void main(String[] args) throws SQLException {
        // Create the "url"
        // assume database server is running on the localhost
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "kbtu2023";

        // Create a simple query
        String query = "select * from EMPLOYEE where ID > 101 and (FIRSTNAME) != 'Michael'";
        String insert = "INSERT INTO EMPLOYEE (ID, firstname, lastname, birthdate, salary) VALUES(?, ?, ?, ?, ?)";
        // A try-with-resources example
        // Connection and Statement implement java.lan.AutoCloseable
        Connection con = DriverManager.getConnection(url, username, password);
        try (con) {
            PreparedStatement statement = con.prepareStatement(insert);
            String dateString = "2005-09-18";
            java.sql.Date date = java.sql.Date.valueOf(dateString);
            statement.setInt(1, 203);
            statement.setString(2, "Olzhas");
            statement.setString(3, "Koshkarbay");
            statement.setDate(4, date);
            statement.setDouble(5, 2000.00);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int empID = rs.getInt("ID");
                String first = rs.getString("FIRSTNAME");
                String last = rs.getString("LASTNAME");
                Date birth_date = rs.getDate("BIRTHDATE");
                float salary = rs.getFloat("SALARY");
                System.out.println("Employee ID:   " + empID + "\n"
                        + "Employee Name: " + first.trim() + " " + last.trim() + "\n"
                        + "Birth Date:    " + birth_date + "\n"
                        + "Salary:        " + salary + "\n");

            }
//            int rowsInserted = statement.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("Данные успешно вставлены!");
//            }
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }}
}