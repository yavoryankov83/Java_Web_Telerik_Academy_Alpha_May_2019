package com.employeeproject.services;

import com.employeeproject.exceptions.BadRequestException;
import com.employeeproject.exceptions.ConflictException;
import com.employeeproject.exceptions.NotFoundException;
import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.employeeproject.repositories.contracts.EmployeeRepository;
import com.employeeproject.repositories.contracts.ProjectRepository;
import com.employeeproject.services.contracts.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

  private static final String PROJECT_NOT_FOUND_EXCEPTION =
          "Project with ID %d does not exist.";
  private static final String EMPLOYEE_NOT_FOUND_EXCEPTION =
          "Employee with ID %d does not exist.";
  private static final String PROJECT_ALREADY_EXIST_EXCEPTION =
          "Project with ID %d already exists.";
  private static final String EMPLOYEE_ALREADY_ASSIGNED_EXCEPTION =
          "Employee with ID %d is already assigned to project with ID %d";
  private static final String EMPLOYEE_NOT_ASSIGNED_EXCEPTION =
          "Employee with ID %d is not assigned to project with ID %d";

  private ProjectRepository projectRepository;
  private EmployeeRepository employeeRepository;

  @Autowired
  public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
    this.projectRepository = projectRepository;
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Map<Integer, ProjectImpl> getAllProjects() {
    return projectRepository.getAllProjects();
  }

  @Override
  public ProjectImpl getProjectById(int projectId) {
    if (!isProjectExists(projectId)) {
      throw new NotFoundException(String.format(PROJECT_NOT_FOUND_EXCEPTION, projectId));
    }
    return getAllProjects().get(projectId);
  }

  @Override
  public void updateProject(int projectId, ProjectImpl project) {
    if (!isProjectExists(projectId)) {
      throw new NotFoundException(String.format(PROJECT_NOT_FOUND_EXCEPTION, projectId));
    }
    ProjectImpl projectToUpdate = getProjectById(projectId);
    projectToUpdate.setName(project.getName());
  }

  @Override
  public void addProject(ProjectImpl project) {
    if (isProjectExists(project.getId())) {
      throw new ConflictException(String.format(PROJECT_ALREADY_EXIST_EXCEPTION, project.getId()));
    }
    projectRepository.addProject(project);
  }

  @Override
  public void deleteProject(int projectId) {
    if (!isProjectExists(projectId)) {
      throw new NotFoundException(String.format(PROJECT_NOT_FOUND_EXCEPTION, projectId));
    }
    projectRepository.getAllEmployeesOfProject(projectId).clear();
    projectRepository.deleteProject(projectId);
  }

  @Override
  public Map<Integer, EmployeeImpl> getAllEmployeesOfProject(int projectId) {
    if (!isProjectExists(projectId)) {
      throw new NotFoundException(String.format(PROJECT_NOT_FOUND_EXCEPTION, projectId));
    }
    return projectRepository.getAllEmployeesOfProject(projectId);
  }

  @Override
  public List<ProjectImpl> projectFilter(Map<String, String> parameters) {
    String name = parameters.get("name");

    if (name != null) {
      return filterByName(name);
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public List<ProjectImpl> projectFilterByMultipleId(List<Integer> id) {
    return projectRepository.getAllProjects().values()
            .stream()
            .filter(project -> id.contains(project.getId()))
            .collect(Collectors.toList());
  }

  @Override
  public List<ProjectImpl> projectSort(Map<String, String> parameters) {
    String sort = parameters.get("sort");

    if (sort.equals("name_asc")) {
      return sortByNameAscending();
    }
    if (sort.equals("name_desc")) {
      return sortByNameDescending();
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public void assignEmployeeToProject(int projectId, int employeeId) {
    if (isEmployeeAssignedToProject(projectId, employeeId)) {
      throw new ConflictException(String.format(EMPLOYEE_ALREADY_ASSIGNED_EXCEPTION, employeeId, projectId));
    }
    EmployeeImpl employeeToAssign = employeeRepository.getAllEmployees().get(employeeId);
    projectRepository.addEmployeeToProject(projectId, employeeToAssign);
  }

  @Override
  public void usAssignEmployeeFromProject(int projectId, int employeeId) {
    if (!isEmployeeAssignedToProject(projectId, employeeId)) {
      throw new BadRequestException(String.format(EMPLOYEE_NOT_ASSIGNED_EXCEPTION, employeeId, projectId));
    }
    projectRepository.deleteEmployeeFromProject(projectId, employeeId);
  }

  @Override
  public boolean isEmployeeAssignedToProject(int projectId, int employeeId) {
    if (!isProjectExists(projectId)) {
      throw new NotFoundException(String.format(PROJECT_NOT_FOUND_EXCEPTION, projectId));
    }
    if (!employeeRepository.getAllEmployees().containsKey(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    return projectRepository.getAllEmployeesOfProject(projectId).containsKey(employeeId);
  }

  private boolean isProjectExists(int id) {
    return projectRepository.projectExists(id);
  }

  private List<ProjectImpl> filterByName(String name) {
    return projectRepository.getAllProjects()
            .values()
            .stream()
            .filter(project -> project.getName().equals(name))
            .collect(Collectors.toList());
  }

  private List<ProjectImpl> sortByNameAscending() {
    return projectRepository.getAllProjects()
            .values()
            .stream()
            .sorted(Comparator.comparing(ProjectImpl::getName))
            .collect(Collectors.toList());
  }

  private List<ProjectImpl> sortByNameDescending() {
    return projectRepository.getAllProjects()
            .values()
            .stream()
            .sorted(Comparator.comparing(ProjectImpl::getName).reversed())
            .collect(Collectors.toList());
  }
}
