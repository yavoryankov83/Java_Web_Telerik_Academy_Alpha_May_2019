package com.employeeproject.services.contracts;

import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

  Map<Integer, Employee> getAllEmployees();

  Employee getEmployeeById(int employeeId);

  void updateEmployee(int employeeId, Employee employee);

  void addEmployee(Employee employee);

  void deleteEmployee(int employeeId);

  Map<Integer, Project> getAllProjectsOfEmployee(int employeeId);

  List<Employee> employeeFilter(Map<String, String> parameters);

  List<Employee> employeeFilterByMultipleId(List<Integer> employeeId);

  List<Employee> employeeSort(Map<String, String> parameters);

  void assignProjectToEmployee(int employeeId, int projectId);

  void usAssignProjectFromEmployee(int employeeId, int projectId);

  boolean isProjectAssignedToEmployee(int employeeId, int projectId);
}
