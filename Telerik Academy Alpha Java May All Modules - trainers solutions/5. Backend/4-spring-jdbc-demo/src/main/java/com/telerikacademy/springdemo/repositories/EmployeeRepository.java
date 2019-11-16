package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    Employee getById(int id);

    List<Employee> getByFirstName(String firstName);

    void addNewEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void removeEmployee(Employee employee);
}
