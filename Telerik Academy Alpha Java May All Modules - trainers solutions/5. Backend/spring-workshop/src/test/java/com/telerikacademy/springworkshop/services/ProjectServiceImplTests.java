package com.telerikacademy.springworkshop.services;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.repositories.contracts.Repository;
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
public class ProjectServiceImplTests {

    @Mock
    Repository<Project> mockRepository;

    @InjectMocks
    ProjectServiceImpl service;

    @Test
    public void create_Should_CreateInRepository_When_ProjectIsValid() {
        //Arrange
        Project project = new Project(1, "project");

        //Act
        service.create(project);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1)).create(project);
    }

    @Test(expected = RuntimeException.class)
    public void create_Should_ThrowException_When_IdIsDuplicate() {
        //Arrange
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "project"));
        Mockito.when(mockRepository.getAll())
                .thenReturn(projects);

        //Act
        service.create(new Project(1, "other-project"));
    }

    @Test(expected = RuntimeException.class)
    public void create_Should_ThrowException_When_NameIsDuplicate() {
        //Arrange
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "project"));
        Mockito.when(mockRepository.getAll())
                .thenReturn(projects);

        //Act
        service.create(new Project(2, "project"));
    }

    @Test
    public void update_Should_UpdateInRepository_When_ProjectIsValid() {
        //Arrange
        Project project = new Project(1, "project");

        //Act
        service.update(1, project);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1)).update(1, project);
    }

    @Test(expected = RuntimeException.class)
    public void update_Should_ThrowException_When_NameIsDuplicate() {
        //Arrange
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "project"));
        Mockito.when(mockRepository.getAll())
                .thenReturn(projects);

        //Act
        service.update(2, new Project(2, "project"));
    }

    @Test
    public void addEmployee_Should_ConnectProjectAndEmployee() {
        //Arrange
        Project project = new Project(1, "project");
        Mockito.when(mockRepository.getById(1))
                .thenReturn(project);

        Employee employee = new Employee(1, "first", "second");
        Repository<Employee> mockEmployeeRepository = Mockito.mock(Repository.class);
        Mockito.when(mockEmployeeRepository.getById(1))
                .thenReturn(employee);

        ProjectServiceImpl service = new ProjectServiceImpl(mockRepository, mockEmployeeRepository);

        //Act
        service.addEmployee(1, employee);

        //Assert
        Assert.assertTrue(project.getEmployees().contains(employee));
        Assert.assertTrue(employee.getProjects().contains(project));
    }

    @Test
    public void removeEmployee_Should_DisconnectProjectAndEmployee() {
        //Arrange
        Project project = new Project(1, "project");
        Employee employee = new Employee(1, "first", "second");

        project.getEmployees().add(employee);
        employee.getProjects().add(project);

        Mockito.when(mockRepository.getById(1))
                .thenReturn(project);

        Repository<Employee> mockEmployeeRepository = Mockito.mock(Repository.class);
        Mockito.when(mockEmployeeRepository.getById(1))
                .thenReturn(employee);

        ProjectServiceImpl service = new ProjectServiceImpl(mockRepository, mockEmployeeRepository);

        //Act
        service.removeEmployee(1, 1);

        //Assert
        Assert.assertFalse(project.getEmployees().contains(employee));
        Assert.assertFalse(employee.getProjects().contains(project));
    }

    @Test
    public void remove_Should_RemoveInRepository() {
        //Arrange
        Project project = new Project(1, "project");

        Mockito.when(mockRepository.getById(1))
                .thenReturn(project);

        //Act
        service.delete(1);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1)).delete(1);
    }

    @Test
    public void remove_Should_DisconnectProjectAndAllConnectedEmployees() {
        //Arrange
        Project project = new Project(1, "project");
        Employee employee1 = new Employee(1, "first", "second");
        Employee employee2 = new Employee(2, "first2", "second2");

        project.getEmployees().add(employee1);
        project.getEmployees().add(employee2);
        employee1.getProjects().add(project);
        employee2.getProjects().add(project);

        Mockito.when(mockRepository.getById(1))
                .thenReturn(project);

        //Act
        service.delete(1);

        //Assert
        Assert.assertFalse(employee1.getProjects().contains(project));
        Assert.assertFalse(employee2.getProjects().contains(project));
    }
}
