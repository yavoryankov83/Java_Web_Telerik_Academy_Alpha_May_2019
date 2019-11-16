package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Department;
import com.telerikacademy.springdemo.models.Employee;

import java.util.List;

public interface DepartmentRepository {
    Department getById(int id);

    List<Employee> getDepartmentEmployees(int id);
}
