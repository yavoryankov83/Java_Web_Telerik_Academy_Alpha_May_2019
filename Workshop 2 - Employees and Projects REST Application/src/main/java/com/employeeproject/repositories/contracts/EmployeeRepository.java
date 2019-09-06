package com.employeeproject.repositories.contracts;

import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public interface EmployeeRepository {

  Map<Integer, Employee> getAllEmployees();

  void addEmployee(Employee employee);

  void deleteEmployee(int id);

  boolean isEmployeeExists(int id);

  void addProjectToEmployee(int employeeId, Project project);

  void deleteProjectFromEmployee(int employeeId, int projectId);

  @JsonIgnore
  Map<Integer, Project> getAllProjectsOfEmployee(int employeeId);
}
