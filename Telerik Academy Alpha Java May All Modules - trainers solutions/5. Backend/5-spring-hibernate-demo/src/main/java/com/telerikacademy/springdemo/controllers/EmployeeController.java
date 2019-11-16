package com.telerikacademy.springdemo.controllers;

import com.telerikacademy.springdemo.exceptions.EntityNotFoundException;
import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.models.Project;
import com.telerikacademy.springdemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/projects")
    public List<Project> getEmployeeProjects(@PathVariable int id) {
        return service.getEmployeeProjects(id);
    }

    @GetMapping("/filter")
    public List<Employee> getByName(@RequestParam String firstName) {
        return service.getByName(firstName);
    }

    @PostMapping
    public void create(@Valid @RequestBody Employee employee) {
        try {
            service.addNewEmployee(employee);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        try {
            service.removeEmployee(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
