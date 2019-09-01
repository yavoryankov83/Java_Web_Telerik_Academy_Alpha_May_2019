package com.employeeproject.services.contracts;

import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

  Map<Integer, EmployeeImpl> getAllEmployees();

  EmployeeImpl getEmployeeById(int employeeId);

  void updateEmployee(int employeeId, EmployeeImpl employee);

  void addEmployee(EmployeeImpl employee);

  void deleteEmployee(int employeeId);

  Map<Integer, ProjectImpl> getAllProjectsOfEmployee(int employeeId);

  List<EmployeeImpl> employeeFilter(Map<String, String> parameters);

  List<EmployeeImpl> employeeFilterByMultipleId(List<Integer> employeeId);

  List<EmployeeImpl> employeeSort(Map<String, String> parameters);

  void assignProjectToEmployee(int employeeId, int projectId);

  void usAssignProjectFromEmployee(int employeeId, int projectId);

  boolean isProjectAssignedToEmployee(int employeeId, int projectId);
}
