package com.telerikacademy.springworkshop.services;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.repositories.contracts.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTests {

    @Mock
    EmployeeRepository mockRepository;

    @InjectMocks
    EmployeeServiceImpl service;

    @Test
    public void create_Should_CreateInRepository_When_EmployeeIsValid() {
        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");

        //Act
        service.create(employee);

        //Assert
        verify(mockRepository, times(1)).create(employee);
    }

    @Test(expected = RuntimeException.class)
    public void create_Should_ThrowException_When_IdIsDuplicate() {
        //Arrange
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Test", "Employee"));

        when(mockRepository.getAll()).thenReturn(employees);

        //Act
        service.create(new Employee(1, "New", "Emp"));
    }


    @Test
    public void update_Should_UpdateInRepository_When_EmployeeIsValid() {
        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");

        //Act
        service.update(1, employee);

        //Assert
        verify(mockRepository, times(1)).update(1, employee);

    }

    @Test
    public void delete_Should_DeleteInRepository_When_EmployeeIsValid() {
        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");
        when(mockRepository.getById(1)).thenReturn(employee);

        //Act
        service.delete(1);

        //Assert
        verify(mockRepository, times(1)).delete(1);
    }

    @Test
    public void delete_Should_RemoveEmployeeFromProjects() {
        //Arrange
        Project project = new Project(1, "Project");
        Employee employee1 = new Employee(1, "Test1", "Employee1");
        Employee employee2 = new Employee(2, "Test2", "Employee2");

        project.getEmployees().add(employee1);
        project.getEmployees().add(employee2);
        employee1.getProjects().add(project);
        employee2.getProjects().add(project);

        when(mockRepository.getById(1)).thenReturn(employee1);

        //Act
        service.delete(1);

        //Assert
        assertFalse(project.getEmployees().contains(employee1));
        assertTrue(project.getEmployees().contains(employee2));
    }


}
