package com.telerikacademy.springworkshop.repositories;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.repositories.contracts.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("EmployeeRepository")
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Employee> employees;

    public EmployeeRepositoryImpl() {
        InitialData data = new InitialData();
        employees = data.getEmployees();
    }

    @Override
    public void create(Employee employee) {

        employees.add(employee);
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

    @Override
    public Employee getById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Employee with id %d does not exist.", id)));
    }

    @Override
    public void update(int id, Employee employee) {
        Employee employeeToUpdate = getById(id);
        employeeToUpdate.setFirstName(employee.getFirstName());
        employeeToUpdate.setLastName(employee.getLastName());
    }

    @Override
    public void delete(int id) {
        Employee employeeToDelete = getById(id);
        employees.remove(employeeToDelete);
    }

    @Override
    public List<Project> getProjectsForEmployee(int id) {
        return getById(id).getProjects();
    }

    @Override
    public List<Employee> get(String firstName, String lastName) {

        List<Employee> filteredEmployees = new ArrayList<>();

        if (firstName != null) {

            filteredEmployees = employees.stream()
                    .filter(employee -> employee.getFirstName().equalsIgnoreCase(firstName))
                    .collect(Collectors.toList());

            if (lastName != null) {
                filteredEmployees = filteredEmployees.stream()
                        .filter(employee -> employee.getLastName().equalsIgnoreCase(lastName))
                        .collect(Collectors.toList());
            }


        } else if (lastName != null) {
            filteredEmployees = employees.stream()
                    .filter(employee -> employee.getLastName().equalsIgnoreCase(lastName))
                    .collect(Collectors.toList());

        }

        return filteredEmployees;

    }

}
