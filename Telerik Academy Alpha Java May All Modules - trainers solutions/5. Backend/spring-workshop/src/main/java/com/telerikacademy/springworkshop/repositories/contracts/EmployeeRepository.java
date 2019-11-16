package com.telerikacademy.springworkshop.repositories.contracts;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee> {

    List<Employee> get(String firstName, String lastName);

    List<Project> getProjectsForEmployee(int id);
}
