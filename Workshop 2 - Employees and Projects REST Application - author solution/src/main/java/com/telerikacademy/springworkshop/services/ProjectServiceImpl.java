package com.telerikacademy.springworkshop.services;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.repositories.contracts.Repository;
import com.telerikacademy.springworkshop.services.contracts.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private Repository<Project> repository;
    private Repository<Employee> employeeRepository;

    @Autowired
    public ProjectServiceImpl(
            @Qualifier("ProjectRepository") Repository<Project> repository,
            @Qualifier("EmployeeRepository") Repository<Employee> employeeRepository
    ) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void create(Project project) {
        checkDuplicateId(project);

        checkDuplicateName(project);

        repository.create(project);
    }

    @Override
    public List<Project> getAll() {
        return repository.getAll();
    }

    @Override
    public Project getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void update(int id, Project project) {
        checkDuplicateName(project);

        repository.update(id, project);
    }

    @Override
    public void addEmployee(int id, Employee employee) {
        Project project = repository.getById(id);
        Employee relatedEmployee = employeeRepository.getById(employee.getId());

        project.getEmployees().add(relatedEmployee);
        relatedEmployee.getProjects().add(project);
    }

    @Override
    public void removeEmployee(int id, int employeeId) {
        Project project = repository.getById(id);
        Employee relatedEmployee = employeeRepository.getById(employeeId);

        project.getEmployees().remove(relatedEmployee);
        relatedEmployee.getProjects().remove(project);
    }

    @Override
    public void delete(int id) {
        Project projectToDelete = repository.getById(id);
        for (Employee employee : projectToDelete.getEmployees()) {
            employee.getProjects().remove(projectToDelete);
        }
        repository.delete(id);
    }

    private void checkDuplicateId(Project project) {
        List<Project> projectsWithSameId = repository.getAll().stream()
                .filter(p -> p.getId() == project.getId())
                .collect(Collectors.toList());

        if (projectsWithSameId.size() > 0) {
            throw new RuntimeException(
                    String.format("Project with %d id already exists.", project.getId()));
        }
    }

    private void checkDuplicateName(Project project) {
        List<Project> projectsWithSameName = repository.getAll().stream()
                .filter(p -> p.getName().equals(project.getName()))
                .collect(Collectors.toList());

        if (projectsWithSameName.size() > 0) {
            throw new RuntimeException(
                    String.format("Project with %s name already exists.", project.getName()));
        }
    }
}
