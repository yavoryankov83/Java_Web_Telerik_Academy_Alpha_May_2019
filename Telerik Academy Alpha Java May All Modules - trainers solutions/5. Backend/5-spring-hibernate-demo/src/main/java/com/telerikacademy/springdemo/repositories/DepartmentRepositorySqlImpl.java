package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Department;
import com.telerikacademy.springdemo.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DepartmentRepositorySqlImpl implements DepartmentRepository {
    SessionFactory sessionFactory;

    @Autowired
    public DepartmentRepositorySqlImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Department getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, id);
        }
    }

    @Override
    public List<Employee> getDepartmentEmployees(int id) {
        try (Session session = sessionFactory.openSession()) {
            Department department = session.get(Department.class, id);
            return new ArrayList<>(department.getEmployees());
        }
    }
}
