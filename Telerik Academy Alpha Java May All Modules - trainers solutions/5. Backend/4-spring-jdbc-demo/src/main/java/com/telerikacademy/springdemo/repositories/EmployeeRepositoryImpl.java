package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Employee;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

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
    public Employee getById(int id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Employee> getByFirstName(String firstName) {
        throw new NotImplementedException();
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
}
