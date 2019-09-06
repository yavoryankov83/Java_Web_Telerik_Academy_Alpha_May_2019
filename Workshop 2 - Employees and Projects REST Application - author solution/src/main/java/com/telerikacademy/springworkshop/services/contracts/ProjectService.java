package com.telerikacademy.springworkshop.services.contracts;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;

import java.util.List;

public interface ProjectService {
    void create(Project project);

    List<Project> getAll();

    Project getById(int id);

    void update(int id, Project project);

    void addEmployee(int id, Employee employee);

    void removeEmployee(int id, int employeeId);

    void delete(int id);
}
