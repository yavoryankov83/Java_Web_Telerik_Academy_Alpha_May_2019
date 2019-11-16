package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class EmployeeRepositorySqlImpl implements EmployeeRepository {
    private String dbUrl, dbUsername, dbPassword;

    @Autowired
    public EmployeeRepositorySqlImpl(Environment env) {
        dbUrl = env.getProperty("database.url");
        dbUsername = env.getProperty("database.username");
        dbPassword = env.getProperty("database.password");
    }

    @Override
    public List<Employee> getAll() {
        String sql = "select EmployeeID, FirstName, LastName from employees";
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql)
        ) {
            return getEmployees(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getById(int id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Employee> getByFirstName(String firstName) {
        String sql = "select EmployeeID, FirstName, LastName from employees where FirstName = ?";
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, firstName);
            try (
                    ResultSet result = statement.executeQuery()
            ) {
                return getEmployees(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNewEmployee(Employee employee) {
        String sql = "insert into employees (FirstName, LastName)\n" +
                "values (?, ?)";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "update employees\n" +
                "set FirstName = ?, LastName = ?\n" +
                "where EmployeeID = ?";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeEmployee(Employee employee) {

    }

    private List<Employee> getEmployees(ResultSet result) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        while (result.next()) {
            Employee employee = new Employee(
                    result.getInt("EmployeeID"),
                    result.getString("FirstName"),
                    result.getString("LastName")
            );
            employees.add(employee);
        }
        return employees;
    }
}
