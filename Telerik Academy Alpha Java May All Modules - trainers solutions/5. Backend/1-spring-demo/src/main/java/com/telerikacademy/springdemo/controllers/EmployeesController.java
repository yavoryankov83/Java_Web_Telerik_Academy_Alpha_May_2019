package com.telerikacademy.springdemo.controllers;

import com.telerikacademy.springdemo.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private List<Employee> employees;

    public EmployeesController() {
        employees = new ArrayList<>();

        employees.add(new Employee(1, "Petar", "Petrov"));
        employees.add(new Employee(2, "Ivan", "Ivanov"));
        employees.add(new Employee(3, "Petar", "Ivanov"));
    }

    @GetMapping
    public List<Employee> getAll() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Employee with id %d does not exist.", id)));
    }

    @PostMapping
    public void create(@Valid @RequestBody Employee employee) {
        employees.add(employee);
    }
}
