package com.telerikacademy.springdemo.controllers.rest;

import com.telerikacademy.springdemo.models.Department;
import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {
    private DepartmentRepository repository;

    @Autowired
    public DepartmentRestController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable int id) {
        return repository.getById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getDepartmentEmployees(@PathVariable int id) {
        return repository.getDepartmentEmployees(id);
    }
}
