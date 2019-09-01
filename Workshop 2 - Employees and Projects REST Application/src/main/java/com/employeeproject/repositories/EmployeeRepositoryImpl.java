package com.employeeproject.repositories;

import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.employeeproject.repositories.contracts.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

  private Map<Integer, EmployeeImpl> employees;
  private Map<Integer, Map<Integer, ProjectImpl>> employeeProjects;

  public EmployeeRepositoryImpl() {
    employees = new HashMap<>();
    employeeProjects = new HashMap<>();

    employees.put(1, new EmployeeImpl(1, "Ivan", "Todorov"));
    employees.put(2, new EmployeeImpl(2, "Penka", "Minkova"));
    employees.put(3, new EmployeeImpl(3, "Ralica", "Stoqnova"));
    employees.put(4, new EmployeeImpl(4, "Rosen", "Rosenov"));
    employees.put(5, new EmployeeImpl(5, "Rosen", "Yankov"));

    employeeProjects.put(1, new HashMap<>());
    employeeProjects.put(2, new HashMap<>());
    employeeProjects.put(3, new HashMap<>());
    employeeProjects.put(4, new HashMap<>());
    employeeProjects.put(5, new HashMap<>());
  }

  @Override
  public Map<Integer, EmployeeImpl> getAllEmployees() {
    return employees;
  }

  @Override
  public void addEmployee(EmployeeImpl employee) {
    employees.put(employee.getId(), employee);
    employeeProjects.put(employee.getId(), new HashMap<>());
  }

  @Override
  public void deleteEmployee(int employeeId) {
    employees.remove(employeeId);
    employeeProjects.remove(employeeId);
  }

  @Override
  public boolean isEmployeeExists(int employeeId) {
    return employees.containsKey(employeeId);
  }

  @Override
  public void addProjectToEmployee(int employeeId, ProjectImpl project) {
    employeeProjects.get(employeeId).put(project.getId(), project);
  }

  @Override
  public void deleteProjectFromEmployee(int employeeId, int projectId) {
    employeeProjects.get(employeeId).remove(projectId);
  }

  @Override
  @JsonIgnore
  public Map<Integer, ProjectImpl> getAllProjectsOfEmployee(int employeeId) {
    return employeeProjects.get(employeeId);
  }
}
