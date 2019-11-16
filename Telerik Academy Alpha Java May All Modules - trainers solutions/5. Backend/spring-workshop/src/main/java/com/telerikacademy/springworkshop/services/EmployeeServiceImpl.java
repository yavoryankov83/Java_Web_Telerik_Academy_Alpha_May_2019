package com.telerikacademy.springworkshop.services;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.repositories.contracts.EmployeeRepository;
import com.telerikacademy.springworkshop.repositories.contracts.Repository;
import com.telerikacademy.springworkshop.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Repository<Project> projectRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(
            @Qualifier("ProjectRepository") Repository<Project> projectRepository,
            @Qualifier("EmployeeRepository") EmployeeRepository employeeRepository
    ) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> get(String firstName, String secondName) {


        if (firstName == null && secondName == null) {
            return getAll();
        }

        return employeeRepository.get(firstName, secondName);

    }

    @Override
    public void create(Employee employee) {

        checkDuplicateId(employee);

        employeeRepository.create(employee);

    }

    @Override
    public Employee getById(int id) {
        return employeeRepository.getById(id);
    }

    public List<Project> getProjectsForEmployee(int id) {
        return employeeRepository.getProjectsForEmployee(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @Override
    public void update(int id, Employee employee) {

        employeeRepository.update(id, employee);

    }

    @Override
    public void delete(int id) {

        Employee employee = employeeRepository.getById(id);

        employee.getProjects().forEach(project -> project.getEmployees().remove(employee));

        employeeRepository.delete(id);
    }

    private void checkDuplicateId(Employee e) {
        List<Employee> employeesWithSameId = employeeRepository.getAll().stream()
                .filter(employee -> employee.getId() == e.getId())
                .collect(Collectors.toList());

        if (employeesWithSameId.size() > 0) {
            throw new RuntimeException(
                    String.format("Employee with %d id already exists.", e.getId()));
        }

    }

}
