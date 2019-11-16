package com.telerikacademy.springdemo.services;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.models.Project;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(int id);

    List<Employee> getByName(String firstName);

    List<Project> getEmployeeProjects(int id);

    void addNewEmployee(Employee employee);

    void removeEmployee(int id);
}
