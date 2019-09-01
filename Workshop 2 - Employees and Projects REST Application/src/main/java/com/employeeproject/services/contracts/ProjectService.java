package com.employeeproject.services.contracts;

import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;

import java.util.List;
import java.util.Map;

public interface ProjectService {

  Map<Integer, ProjectImpl> getAllProjects();

  ProjectImpl getProjectById(int projectId);

  void updateProject(int projectId, ProjectImpl project);

  void addProject(ProjectImpl project);

  void deleteProject(int projectId);

  Map<Integer, EmployeeImpl> getAllEmployeesOfProject(int projectId);

  List<ProjectImpl> projectFilter(Map<String, String> parameters);

  List<ProjectImpl> projectFilterByMultipleId(List<Integer> projectId);

  List<ProjectImpl> projectSort(Map<String, String> parameters);

  void assignEmployeeToProject(int projectId, int employeeId);

  void usAssignEmployeeFromProject(int projectId, int employeeId);

  boolean isEmployeeAssignedToProject(int projectId, int employeeId);
}
