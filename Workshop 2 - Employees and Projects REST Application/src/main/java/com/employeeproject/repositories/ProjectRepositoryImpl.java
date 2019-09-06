package com.employeeproject.repositories;

import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.employeeproject.repositories.contracts.ProjectRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("ProjectRepository")
public class ProjectRepositoryImpl implements ProjectRepository {

  private Map<Integer, Project> projects;
  private Map<Integer, Map<Integer, Employee>> projectEmployees;

  public ProjectRepositoryImpl() {
    projects = new HashMap<>();
    projectEmployees = new HashMap<>();

    projects.put(1, new Project(1, "Spring MVC"));
    projects.put(2, new Project(2, "Java"));
    projects.put(3, new Project(3, "Database"));
    projects.put(4, new Project(4, "WEB"));
    projects.put(5, new Project(5, "WEB API"));

    projectEmployees.put(1, new HashMap<>());
    projectEmployees.put(2, new HashMap<>());
    projectEmployees.put(3, new HashMap<>());
    projectEmployees.put(4, new HashMap<>());
    projectEmployees.put(5, new HashMap<>());
  }

  @Override
  public Map<Integer, Project> getAllProjects() {
    return projects;
  }

  @Override
  public void addProject(Project project) {
    projects.put(project.getId(), project);
    projectEmployees.put(project.getId(), new HashMap<>());
  }

  @Override
  public void deleteProject(int projectId) {
    projects.remove(projectId);
    projectEmployees.remove(projectId);
  }

  @Override
  public boolean projectExists(int projectId) {
    return projects.containsKey(projectId);
  }

  @Override
  public void addEmployeeToProject(int projectId, Employee employee) {
    if (!projectEmployees.containsKey(projectId)) {
      projectEmployees.put(projectId, new HashMap<>());
    }
    projectEmployees.get(projectId).put(employee.getId(), employee);
  }

  @Override
  public void deleteEmployeeFromProject(int projectId, int employeeId) {
    projectEmployees.get(projectId).remove(employeeId);
  }

  @JsonIgnore
  @Override
  public Map<Integer, Employee> getAllEmployeesOfProject(int projectId) {
    return projectEmployees.get(projectId);
  }
}
