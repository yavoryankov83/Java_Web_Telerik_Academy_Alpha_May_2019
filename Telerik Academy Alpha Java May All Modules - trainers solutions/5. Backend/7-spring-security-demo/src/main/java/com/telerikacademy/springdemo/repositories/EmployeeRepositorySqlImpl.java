package com.telerikacademy.springdemo.repositories;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.models.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class EmployeeRepositorySqlImpl implements EmployeeRepository {
    private SessionFactory sessionFactory;

    public EmployeeRepositorySqlImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Employee getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Employee employee = null;
            try {
                session.beginTransaction();
                employee = session.get(Employee.class, id);
                // more operations
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
            return employee;
        }
    }

    @Override
    public List<Employee> getByName(String firstName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session
                    .createQuery("from Employee where firstName = :firstName", Employee.class);
            query.setParameter("firstName", firstName);
            return query.list();
        }
    }

    @Override
    public List<Employee> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.list();
        }
    }

    @Override
    public List<Project> getEmployeeProjects(int id) {
        try (Session session = sessionFactory.openSession()) {
            Employee employee = session.get(Employee.class, id);
            return new ArrayList<>(employee.getProjects());
        }
    }

    @Override
    public void addNewEmployee(Employee employee) {

    }

    @Override
    public void removeEmployee(Employee employee) {

    }

    @Override
    public boolean exists(int employeeID) {
        return false;
    }
}
