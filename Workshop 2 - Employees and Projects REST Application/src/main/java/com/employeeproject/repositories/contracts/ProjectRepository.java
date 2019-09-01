package com.employeeproject.repositories.contracts;

import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public interface ProjectRepository {

  Map<Integer, ProjectImpl> getAllProjects();

  void addProject(ProjectImpl project);

  void deleteProject(int projectId);

  boolean projectExists(int projectId);

  void addEmployeeToProject(int projectId, EmployeeImpl employee);

  void deleteEmployeeFromProject(int projectId, int employeeId);

  @JsonIgnore
  Map<Integer, EmployeeImpl> getAllEmployeesOfProject(int projectId);
}
