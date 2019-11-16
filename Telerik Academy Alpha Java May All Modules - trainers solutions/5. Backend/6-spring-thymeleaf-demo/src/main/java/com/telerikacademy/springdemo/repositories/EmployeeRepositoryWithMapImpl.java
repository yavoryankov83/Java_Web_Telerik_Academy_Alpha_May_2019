package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.models.Project;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepositoryWithMapImpl implements EmployeeRepository {
    Map<Integer, Employee> myMap;

    public EmployeeRepositoryWithMapImpl() {
        this.myMap = new HashMap<>();

        myMap.put(1, new Employee(1, "Petar", "Petrov"));
        myMap.put(2, new Employee(2, "Ivan", "Ivanov"));
        myMap.put(3, new Employee(3, "Petar", "Ivanov"));
    }

    @Override
    public Employee getById(int id) {
        return myMap.getOrDefault(id, null);
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
        return new ArrayList<>(myMap.values());
    }

    @Override
    public void addNewEmployee(Employee employee) {
        myMap.put(employee.getId(), employee);
    }

    @Override
    public void removeEmployee(Employee employee) {
        myMap.remove(employee.getId());
    }

    @Override
    public boolean exists(int employeeID) {
        return myMap.containsKey(employeeID);
    }
}
