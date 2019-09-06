package com.telerikacademy.springworkshop.controllers.employeeController;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.repositories.contracts.EmployeeRepository;
import com.telerikacademy.springworkshop.services.contracts.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerGetTests {

    @MockBean
    EmployeeService mockService;

    @Mock
    EmployeeRepository employeeRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getById_Should_Return_StatusOk_When_EmployeeExists() throws Exception {
        //Arrange
        when(mockService.getById(1)).thenReturn(new Employee(1, "Test", "Employee"));

        //Act, Assert
        mockMvc.perform(get("/api/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Employee"));
    }

    @Test
    public void getById_Should_Return_StatusNotFound_When_EmployeeDoesNotExist() throws Exception {
        //Arrange
        when(mockService.getById(1)).thenThrow(new RuntimeException());

        //Act, Assert
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmployeeProjects_Should_Return_StatusNotFound_When_EmployeeDoesNotExist() throws Exception {
        //Arrange
        when(mockService.getProjectsForEmployee(7)).thenThrow(new RuntimeException());

        //Act, Assert
        mockMvc.perform(
                get("/api/employees/project/7"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmployeeProjects_Should_Return_StatusOk_When_EmployeeExists() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");

        Project project = new Project(1, "TestProject");
        project.getEmployees().add(employee);
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        when(employeeRepository.getById(1)).thenReturn(employee);
        when(mockService.getProjectsForEmployee(7)).thenReturn(projects);

        //Act, Assert
        mockMvc.perform(
                get("/api/employees/project/7"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"TestProject\"}]"));
    }
}
