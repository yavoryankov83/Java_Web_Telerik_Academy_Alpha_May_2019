package com.telerikacademy.springworkshop.services.contracts;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;

import java.util.List;

public interface EmployeeService {
    List<Employee> get(String firstName, String secondName);

    void create(Employee employee);

    Employee getById(int id);

    List<Employee> getAll();

    void update(int id, Employee employee);

    void delete(int id);

    List<Project> getProjectsForEmployee(int id);
}
