package com.telerikacademy.springdemo.repository;

import com.telerikacademy.springdemo.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Employee> employees;

    public EmployeeRepositoryImpl() {
        this.employees = new ArrayList<>();

        employees.add(new Employee(1, "Petar", "Petrov"));
        employees.add(new Employee(2, "Ivan", "Ivanov"));
        employees.add(new Employee(3, "Petar", "Ivanov"));
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public boolean exists(Employee employee) {
        return employees.contains(employee);
    }

}
