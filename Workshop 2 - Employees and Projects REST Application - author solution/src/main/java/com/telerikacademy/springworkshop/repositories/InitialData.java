package com.telerikacademy.springworkshop.repositories;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;

import java.util.ArrayList;
import java.util.List;

class InitialData {
    private List<Employee> employees;
    private List<Project> projects;

    InitialData() {
        employees = new ArrayList<>();

        Employee e1 = new Employee(1, "Petar", "Petrov");
        Employee e2 = new Employee(2, "Ivan", "Ivanov");
        Employee e3 = new Employee(3, "Petar", "Ivanov");
        Employee e4 = new Employee(4, "Ivan", "Petrov");
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);

        projects = new ArrayList<>();

        Project p1 = new Project(1, "Project 1");
        Project p2 = new Project(2, "Project 2");
        Project p3 = new Project(3, "Project 3");
        projects.add(p1);
        projects.add(p2);
        projects.add(p3);

        e1.getProjects().add(p1);
        p1.getEmployees().add(e1);

        e1.getProjects().add(p2);
        p2.getEmployees().add(e1);

        e2.getProjects().add(p2);
        p2.getEmployees().add(e2);

        e3.getProjects().add(p3);
        p3.getEmployees().add(e3);
    }

    List<Employee> getEmployees() {
        return employees;
    }

    List<Project> getProjects() {
        return projects;
    }
}
