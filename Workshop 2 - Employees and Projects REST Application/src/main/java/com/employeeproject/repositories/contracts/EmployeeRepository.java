package com.employeeproject.repositories.contracts;

import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public interface EmployeeRepository {

  Map<Integer, EmployeeImpl> getAllEmployees();

  void addEmployee(EmployeeImpl employee);

  void deleteEmployee(int id);

  boolean isEmployeeExists(int id);

  void addProjectToEmployee(int employeeId, ProjectImpl project);

  void deleteProjectFromEmployee(int employeeId, int projectId);

  @JsonIgnore
  Map<Integer, ProjectImpl> getAllProjectsOfEmployee(int employeeId);
}
