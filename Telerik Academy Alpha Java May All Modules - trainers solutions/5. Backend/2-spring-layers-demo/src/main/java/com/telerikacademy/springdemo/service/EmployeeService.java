package com.telerikacademy.springdemo.service;

import com.telerikacademy.springdemo.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(int id);

    void addNewEmployee(Employee employee);

    void removeEmployee(int id);
}
