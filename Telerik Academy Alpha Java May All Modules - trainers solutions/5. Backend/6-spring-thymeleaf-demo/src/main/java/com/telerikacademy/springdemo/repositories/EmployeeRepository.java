package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.models.Project;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    Employee getById(int id);

    List<Employee> getByName(String firstName);

    List<Project> getEmployeeProjects(int id);

    void addNewEmployee(Employee employee);

    void removeEmployee(Employee employee);

    boolean exists(int employeeID);
}
