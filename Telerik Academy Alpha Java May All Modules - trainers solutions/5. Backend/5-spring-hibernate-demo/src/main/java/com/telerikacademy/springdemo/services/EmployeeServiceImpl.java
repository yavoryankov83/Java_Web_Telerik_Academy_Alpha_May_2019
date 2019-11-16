package com.telerikacademy.springdemo.services;

import com.telerikacademy.springdemo.exceptions.EntityNotFoundException;
import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.models.Project;
import com.telerikacademy.springdemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAll() {
        return repository.getAll();
    }

    @Override
    public Employee getById(int id) {
        Employee employee = repository.getById(id);
        if (employee == null) {
            throw new EntityNotFoundException(
                    String.format("Employee with id %d does not exist.", id));
        }
        return employee;
    }

    @Override
    public List<Employee> getByName(String firstName) {
        return repository.getByName(firstName);
    }

    @Override
    public List<Project> getEmployeeProjects(int id) {
        return repository.getEmployeeProjects(id);
    }

    @Override
    public void addNewEmployee(Employee employee) {
        if (employeeExists(employee.getId())) {
            throw new IllegalArgumentException("Employee already exists.");
        }

        repository.addNewEmployee(employee);
    }

    @Override
    public void removeEmployee(int id) {

        if (!employeeExists(id)) {
            throw new IllegalArgumentException(String.format("Employee with id %d not found!", id));
        }

        Employee employeeToBeRemoved = getById(id);

        repository.removeEmployee(employeeToBeRemoved);
    }

    private boolean employeeExists(int id) {
        return repository.exists(getById(id));
    }

}
