package com.telerikacademy.springworkshop.controllers;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> filter(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        return employeeService.get(firstName, lastName);

    }

    @GetMapping("/project/{id}")
    public List<Project> getEmployeeProjects(@PathVariable int id) {
        try {
            return employeeService.getProjectsForEmployee(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Employee with id %d does not exits.", id));
        }
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        try {
            return employeeService.getById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Employee with id %d does not exits.", id));
        }
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable int id,
            @RequestBody @Valid Employee employee) {
        try {
            employeeService.update(id, employee);
            return employee;
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public Employee create(@RequestBody @Valid Employee employee) {
        try {
            employeeService.create(employee);
            return employee;
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        try {
            employeeService.delete(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}
