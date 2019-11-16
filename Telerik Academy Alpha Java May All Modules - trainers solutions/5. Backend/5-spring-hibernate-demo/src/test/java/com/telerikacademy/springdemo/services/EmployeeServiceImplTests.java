package com.telerikacademy.springdemo.services;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.repositories.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTests {
    @Mock
    EmployeeRepository mockRepository;

    @InjectMocks
    EmployeeServiceImpl service;

    @Test
    public void getById_Should_ReturnEmployee_When_MatchExists() {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "FirstName1", "LastName1"));
        employees.add(new Employee(2, "FirstName2", "LastName2"));
        employees.add(new Employee(3, "FirstName3", "LastName3"));

        Mockito.when(mockRepository.getAll()).thenReturn(employees);

        // Act
        Employee result = service.getById(2);

        // Assert
        Assert.assertEquals(2, result.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById_Should_ThrowException_When_MatchNotExist() {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "FirstName1", "LastName1"));
        employees.add(new Employee(2, "FirstName2", "LastName2"));
        employees.add(new Employee(3, "FirstName3", "LastName3"));

        Mockito.when(mockRepository.getAll()).thenReturn(employees);

        // Act
        Employee result = service.getById(4);
    }
}
