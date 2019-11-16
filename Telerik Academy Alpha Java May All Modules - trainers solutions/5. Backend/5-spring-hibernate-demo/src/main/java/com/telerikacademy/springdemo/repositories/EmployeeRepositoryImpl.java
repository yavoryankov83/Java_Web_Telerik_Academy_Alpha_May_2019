package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.models.Project;
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
    public Employee getById(int id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Employee> getByName(String firstName) {
        throw new NotImplementedException();
    }

    @Override
    public List<Project> getEmployeeProjects(int id) {
        throw new NotImplementedException();
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
