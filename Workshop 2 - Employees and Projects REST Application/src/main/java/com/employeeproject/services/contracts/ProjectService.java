package com.employeeproject.services.contracts;

import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;

import java.util.List;
import java.util.Map;

public interface ProjectService {

  Map<Integer, Project> getAllProjects();

  Project getProjectById(int projectId);

  void updateProject(int projectId, Project project);

  void addProject(Project project);

  void deleteProject(int projectId);

  Map<Integer, Employee> getAllEmployeesOfProject(int projectId);

  List<Project> projectFilter(Map<String, String> parameters);

  List<Project> projectFilterByMultipleId(List<Integer> projectId);

  List<Project> projectSort(Map<String, String> parameters);

  void assignEmployeeToProject(int projectId, int employeeId);

  void usAssignEmployeeFromProject(int projectId, int employeeId);

  boolean isEmployeeAssignedToProject(int projectId, int employeeId);
}
