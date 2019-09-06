package com.employeeproject.repositories.contracts;

import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public interface ProjectRepository {

  Map<Integer, Project> getAllProjects();

  void addProject(Project project);

  void deleteProject(int projectId);

  boolean projectExists(int projectId);

  void addEmployeeToProject(int projectId, Employee employee);

  void deleteEmployeeFromProject(int projectId, int employeeId);

  @JsonIgnore
  Map<Integer, Employee> getAllEmployeesOfProject(int projectId);
}
