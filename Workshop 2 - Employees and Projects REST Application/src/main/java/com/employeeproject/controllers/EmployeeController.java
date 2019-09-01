package com.employeeproject.controllers;

import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.employeeproject.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {

  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public Map<Integer, EmployeeImpl> getAll() {
    return employeeService.getAllEmployees();
  }

  @GetMapping(path = "{employeeId}/projects")
  public Map<Integer, ProjectImpl> getProjects(@Valid @PathVariable int employeeId) {
    return employeeService.getAllProjectsOfEmployee(employeeId);
  }

  @GetMapping(path = "{employeeId}")
  public EmployeeImpl getById(@Valid @PathVariable int employeeId) {
    return employeeService.getEmployeeById(employeeId);
  }

  @PostMapping
  public void create(@Valid @RequestBody EmployeeImpl employee) {
    employeeService.addEmployee(employee);
  }

  @PutMapping(path = "{employeeId}")
  public void update(@Valid @PathVariable int employeeId, @Valid @RequestBody EmployeeImpl employee) {
    employeeService.updateEmployee(employeeId, employee);
  }

  @DeleteMapping(path = "{employeeId}")
  public void delete(@Valid @PathVariable int employeeId) {
    employeeService.deleteEmployee(employeeId);
  }

  @PutMapping(path = "{employeeId}/assign_project/{projectId}")
  public void assign(@Valid @PathVariable int employeeId, @Valid @PathVariable int projectId) {
    employeeService.assignProjectToEmployee(employeeId, projectId);
  }

  @PutMapping(path = "{employeeId}/unAssign_project/{projectId}")
  public void unAssign(@Valid @PathVariable int employeeId, @Valid @PathVariable int projectId) {
    employeeService.usAssignProjectFromEmployee(employeeId, projectId);
  }

  @GetMapping(path = "filtered")
  public List<EmployeeImpl> filter(@Valid @RequestParam Map<String, String> filterParams) {
    return employeeService.employeeFilter(filterParams);
  }

  @GetMapping(path = "id_filter")
  public List<EmployeeImpl> getAllById(@Valid @RequestParam(name = "id") List<Integer> employeeId) {
    return employeeService.employeeFilterByMultipleId(employeeId);
  }

  @GetMapping(path = "sorted")
  public List<EmployeeImpl> sort(@Valid @RequestParam Map<String, String> sortParams) {
    return employeeService.employeeSort(sortParams);
  }
}