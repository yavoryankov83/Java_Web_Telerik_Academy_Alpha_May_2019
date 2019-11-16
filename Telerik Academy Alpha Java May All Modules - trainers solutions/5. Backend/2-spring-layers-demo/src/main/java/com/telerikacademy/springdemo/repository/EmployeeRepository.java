package com.telerikacademy.springdemo.repository;

import com.telerikacademy.springdemo.models.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    void addNewEmployee(Employee employee);
    void removeEmployee(Employee employee);
    boolean exists(Employee employee);
}
