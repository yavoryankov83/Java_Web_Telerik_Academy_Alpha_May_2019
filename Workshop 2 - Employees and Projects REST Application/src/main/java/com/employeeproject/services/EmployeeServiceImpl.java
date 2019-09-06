package com.employeeproject.services;

import com.employeeproject.exceptions.BadRequestException;
import com.employeeproject.exceptions.ConflictException;
import com.employeeproject.exceptions.NotFoundException;
import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.employeeproject.repositories.contracts.EmployeeRepository;
import com.employeeproject.repositories.contracts.ProjectRepository;
import com.employeeproject.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
  public EmployeeServiceImpl(@Qualifier("EmployeeRepository") EmployeeRepository employeeRepository,
                             @Qualifier("ProjectRepository") ProjectRepository projectRepository) {
    this.employeeRepository = employeeRepository;
    this.projectRepository = projectRepository;
  }

  @Override
  public Map<Integer, Employee> getAllEmployees() {
    return employeeRepository.getAllEmployees();
  }

  @Override
  public Employee getEmployeeById(int employeeId) {
    if (!isEmployeeExists(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    return employeeRepository.getAllEmployees().get(employeeId);
  }

  @Override
  public void updateEmployee(int employeeId, Employee employee) {
    if (!isEmployeeExists(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    Employee employeeToUpdate = getEmployeeById(employeeId);
    if (employee.getFirstName() != null) {
      employeeToUpdate.setFirstName(employee.getFirstName());
    }
    if (employee.getLastName() != null) {
      employeeToUpdate.setLastName(employee.getLastName());
    }
  }

  @Override
  public void addEmployee(Employee employee) {
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
  public Map<Integer, Project> getAllProjectsOfEmployee(int employeeId) {
    if (!isEmployeeExists(employeeId)) {
      throw new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_EXCEPTION, employeeId));
    }
    return employeeRepository.getAllProjectsOfEmployee(employeeId);
  }

  @Override
  public List<Employee> employeeFilter(Map<String, String> parameters) {
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
  public List<Employee> employeeFilterByMultipleId(List<Integer> employeeId) {
    return employeeRepository.getAllEmployees().values()
            .stream()
            .filter(employee -> employeeId.contains(employee.getId()))
            .collect(Collectors.toList());
  }

  @Override
  public List<Employee> employeeSort(Map<String, String> parameters) {
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
    Project projectToAssign = projectRepository.getAllProjects().get(projectId);
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

  private List<Employee> filterByFirstName(String firstName) {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .filter(employee -> employee.getFirstName().equals(firstName))
            .collect(Collectors.toList());
  }

  private List<Employee> filterByLastName(String lastName) {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .filter(employee -> employee.getLastName().equals(lastName))
            .collect(Collectors.toList());
  }

  private List<Employee> filterByFirstAndLastName(String firstName, String lastName) {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .filter(employee -> employee.getFirstName().equals(firstName))
            .filter(employee -> employee.getLastName().equals(lastName))
            .collect(Collectors.toList());
  }

  private List<Employee> sortByFirstNameAscending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(Employee::getFirstName))
            .collect(Collectors.toList());
  }

  private List<Employee> sortByFirstNameDescending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(Employee::getFirstName).reversed())
            .collect(Collectors.toList());
  }

  private List<Employee> sortByLastNameAscending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(Employee::getLastName))
            .collect(Collectors.toList());
  }

  private List<Employee> sortByLastNameDescending() {
    return employeeRepository.getAllEmployees()
            .values()
            .stream()
            .sorted(Comparator.comparing(Employee::getLastName).reversed())
            .collect(Collectors.toList());
  }
}
