package com.employeeproject.controllers;

import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.employeeproject.services.contracts.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

  private ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping
  public Map<Integer, ProjectImpl> getAll() {
    return projectService.getAllProjects();
  }

  @GetMapping(path = "{id}/employees")
  public Map<Integer, EmployeeImpl> getEmployees(@Valid @PathVariable int id) {
    return projectService.getAllEmployeesOfProject(id);
  }

  @GetMapping(path = "{projectId}")
  public ProjectImpl getById(@Valid @PathVariable int projectId) {
    return projectService.getProjectById(projectId);
  }

  @PostMapping
  public void create(@Valid @RequestBody ProjectImpl project) {
    projectService.addProject(project);
  }

  @PutMapping(path = "{projectId}")
  public void update(@Valid @PathVariable int projectId, @Valid @RequestBody ProjectImpl projectDetails) {
    projectService.updateProject(projectId, projectDetails);
  }

  @DeleteMapping(path = "{projectId}")
  public void delete(@Valid @PathVariable int projectId) {
    projectService.deleteProject(projectId);
  }

  @PutMapping(path = "{projectId}/assign_employee/{employeeId}")
  public void assign(@Valid @PathVariable int projectId, @Valid @PathVariable int employeeId) {
    projectService.assignEmployeeToProject(projectId, employeeId);
  }

  @PutMapping(path = "{projectId}/unAssign_employee/{employeeId}")
  public void unAssign(@Valid @PathVariable int projectId, @Valid @PathVariable int employeeId) {
    projectService.usAssignEmployeeFromProject(projectId, employeeId);
  }

  @GetMapping(path = "filtered")
  public List<ProjectImpl> filter(@Valid @RequestParam Map<String, String> filterParams) {
    return projectService.projectFilter(filterParams);
  }

  @GetMapping(path = "id_filter")
  public List<ProjectImpl> getAllById(@Valid @RequestParam(name = "id") List<Integer> projectId) {
    return projectService.projectFilterByMultipleId(projectId);
  }

  @GetMapping(path = "sorted")
  public List<ProjectImpl> sort(@Valid @RequestParam Map<String, String> sortParams) {
    return projectService.projectSort(sortParams);
  }
}