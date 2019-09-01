package com.employeeproject.services;

import com.employeeproject.exceptions.BadRequestException;
import com.employeeproject.exceptions.ConflictException;
import com.employeeproject.exceptions.NotFoundException;
import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.employeeproject.repositories.contracts.EmployeeRepository;
import com.employeeproject.repositories.contracts.ProjectRepository;
import com.employeeproject.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private static final String EMPLOYEE_NOT_FOUND_EXCEPTION =
          "Employee with ID %d does not exist.";
  private static final String PROJECT_NOT_FOUND_EXCEPTION =
          "Project with ID %d does not exist.";
  private static final String EMPLOYEE_ALREADY_EXIST_EXCEPTION =
          "Employee with ID %d already exists.";
  private static final String PROJECT_ALREADY_ASSIGNED_EXCEPTION =
          "Project with ID %d is already assigned to employee with ID %d";
  private static final String PROJECT_NOT_ASSIGNED_EXCEPTION =
          "Project with ID %d is not assigned to employee with ID %d";

  private EmployeeRepository employeeRepository;
  private ProjectRepository projectRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
    this.employeeRepository = employeeRepository;
    this.projectRepository = projectRepository;
  }

  @Override
  public Map<Integer, EmployeeImpl> getAllEmployees() {
    return employeeRepository.getAllEmployees();
  }

  @Override
  public EmployeeImpl getEmployeeById(int employeeId) {
    if (!isEmployeeExists(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    return employeeRepository.getAllEmployees().get(employeeId);
  }

  @Override
  public void updateEmployee(int employeeId, EmployeeImpl employee) {
    if (!isEmployeeExists(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    EmployeeImpl employeeToUpdate = getEmployeeById(employeeId);
    if (employee.getFirstName() != null) {
      employeeToUpdate.setFirstName(employee.getFirstName());
    }
    if (employee.getLastName() != null) {
      employeeToUpdate.setLastName(employee.getLastName());
    }
  }

  @Override
  public void addEmployee(EmployeeImpl employee) {
    if (isEmployeeExists(employee.getId())) {
      throw new ConflictException(String.format(EMPLOYEE_ALREADY_EXIST_EXCEPTION, employee.getId()));
    }
    employeeRepository.addEmployee(employee);
  }

  @Override
  public void deleteEmployee(int employId) {
    if (!isEmployeeExists(employId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employId));
    }
    employeeRepository.getAllProjectsOfEmployee(employId).clear();
    employeeRepository.deleteEmployee(employId);
  }

  @Override
  public Map<Integer, ProjectImpl> getAllProjectsOfEmployee(int employeeId) {
    if (!isEmployeeExists(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    return employeeRepository.getAllProjectsOfEmployee(employeeId);
  }

  @Override
  public List<EmployeeImpl> employeeFilter(Map<String, String> parameters) {
    String firstName = parameters.get("firstName");
    String lastName = parameters.get("lastName");

    if (firstName != null && lastName != null) {
      return filterByFirstAndLastName(firstName, lastName);
    }
    if (firstName != null) {
      return filterByFirstName(firstName);
    }
    if (lastName != null) {
      return filterByLastName(lastName);
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public List<EmployeeImpl> employeeFilterByMultipleId(List<Integer> employeeId) {
    return employeeRepository.getAllEmployees().values()
            .stream()
            .filter(employee -> employeeId.contains(employee.getId()))
            .collect(Collectors.toList());
  }

  @Override
  public List<EmployeeImpl> employeeSort(Map<String, String> parameters) {
    String sort = parameters.get("sort");

    if (sort.equals("firstName_asc")) {
      return sortByFirstNameAscending();
    }
    if (sort.equals("firstName_desc")) {
      return sortByFirstNameDescending();
    }
    if (sort.equals("lastName_asc")) {
      return sortByLastNameAscending();
    }
    if (sort.equals("lastName_desc")) {
      return sortByLastNameDescending();
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public void assignProjectToEmployee(int employeeId, int projectId) {
    if (isProjectAssignedToEmployee(employeeId, projectId)) {
      throw new ConflictException(String.format(PROJECT_ALREADY_ASSIGNED_EXCEPTION, projectId, employeeId));
    }
    ProjectImpl projectToAssign = projectRepository.getAllProjects().get(projectId);
    employeeRepository.addProjectToEmployee(employeeId, projectToAssign);
  }

  @Override
  public void usAssignProjectFromEmployee(int employeeId, int projectId) {
    if (!isProjectAssignedToEmployee(employeeId, projectId)) {
      throw new BadRequestException(String.format(PROJECT_NOT_ASSIGNED_EXCEPTION, projectId, employeeId));
    }
    employeeRepository.deleteProjectFromEmployee(employeeId, projectId);
  }

  @Override
  public boolean isProjectAssignedToEmployee(int employeeId, int projectId) {
    if (!isEmployeeExists(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    if (!projectRepository.getAllProjects().containsKey(projectId)) {
      throw new NotFoundException(String.format(PROJECT_NOT_FOUND_EXCEPTION, projectId));
    }
    return employeeRepository.getAllProjectsOfEmployee(employeeId).containsKey(projectId);
  }

  private boolean isEmployeeExists(int employeeId) {
    return employeeRepository.isEmployeeExists(employeeId);
  }

  private List<EmployeeImpl> filterByFirstName(String firstName) {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .filter(employee -> employee.getFirstName().equals(firstName))
            .collect(Collectors.toList());
  }

  private List<EmployeeImpl> filterByLastName(String lastName) {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .filter(employee -> employee.getLastName().equals(lastName))
            .collect(Collectors.toList());
  }

  private List<EmployeeImpl> filterByFirstAndLastName(String firstName, String lastName) {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .filter(employee -> employee.getFirstName().equals(firstName))
            .filter(employee -> employee.getLastName().equals(lastName))
            .collect(Collectors.toList());
  }

  private List<EmployeeImpl> sortByFirstNameAscending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(EmployeeImpl::getFirstName))
            .collect(Collectors.toList());
  }

  private List<EmployeeImpl> sortByFirstNameDescending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(EmployeeImpl::getFirstName).reversed())
            .collect(Collectors.toList());
  }

  private List<EmployeeImpl> sortByLastNameAscending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(EmployeeImpl::getLastName))
            .collect(Collectors.toList());
  }

  private List<EmployeeImpl> sortByLastNameDescending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(EmployeeImpl::getLastName).reversed())
            .collect(Collectors.toList());
  }
}
