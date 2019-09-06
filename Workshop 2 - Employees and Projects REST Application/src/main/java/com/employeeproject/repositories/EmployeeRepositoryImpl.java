package com.employeeproject.repositories;

import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.employeeproject.repositories.contracts.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("EmployeeRepository")
public class EmployeeRepositoryImpl implements EmployeeRepository {

  private Map<Integer, Employee> employees;
  private Map<Integer, Map<Integer, Project>> employeeProjects;

  public EmployeeRepositoryImpl() {
    employees = new HashMap<>();
    employeeProjects = new HashMap<>();

    employees.put(1, new Employee(1, "Ivan", "Todorov"));
    employees.put(2, new Employee(2, "Penka", "Minkova"));
    employees.put(3, new Employee(3, "Ralica", "Stoqnova"));
    employees.put(4, new Employee(4, "Rosen", "Rosenov"));
    employees.put(5, new Employee(5, "Rosen", "Yankov"));

    employeeProjects.put(1, new HashMap<>());
    employeeProjects.put(2, new HashMap<>());
    employeeProjects.put(3, new HashMap<>());
    employeeProjects.put(4, new HashMap<>());
    employeeProjects.put(5, new HashMap<>());
  }

  @Override
  public Map<Integer, Employee> getAllEmployees() {
    return employees;
  }

  @Override
  public void addEmployee(Employee employee) {
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
  public void addProjectToEmployee(int employeeId, Project project) {
    employeeProjects.get(employeeId).put(project.getId(), project);
  }

  @Override
  public void deleteProjectFromEmployee(int employeeId, int projectId) {
    employeeProjects.get(employeeId).remove(projectId);
  }

  @Override
  @JsonIgnore
  public Map<Integer, Project> getAllProjectsOfEmployee(int employeeId) {
    return employeeProjects.get(employeeId);
  }
}
