package com.telerikacademy.springdemo.services;

import com.telerikacademy.springdemo.models.Employee;
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
        return repository
                .getAll()
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format("Employee with id %d does not exist.", id)));
    }

    @Override
    public List<Employee> getByFirstName(String firstName) {
        return repository.getByFirstName(firstName);
    }

    @Override
    public void addNewEmployee(Employee employee) {
        if (employeeExists(employee.getId())) {
            throw new IllegalArgumentException("Employee already exists.");
        }

        repository.addNewEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        repository.updateEmployee(employee);
    }

    @Override
    public void removeEmployee(int id) {
        Employee employeeToBeRemoved = getById(id);

        repository.removeEmployee(employeeToBeRemoved);
    }

    private boolean employeeExists(int id) {
        boolean employeeExists = false;
        try {
            repository.getById(id);
            employeeExists = true;
        } catch (IllegalArgumentException e) {
        }
        return employeeExists;
    }
}
