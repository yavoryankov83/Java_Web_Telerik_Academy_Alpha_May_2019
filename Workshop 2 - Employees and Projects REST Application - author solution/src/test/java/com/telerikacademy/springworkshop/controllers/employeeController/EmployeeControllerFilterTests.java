package com.telerikacademy.springworkshop.controllers.employeeController;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.services.contracts.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerFilterTests {

    @MockBean
    EmployeeService mockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void filterBy_Should_Return_StatusOk_When_NoArgumentsPassed() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(mockService.getAll()).thenReturn(employees);

        //Act, Assert
        mockMvc.perform(
                get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"firstName\":\"Test\"}]"));

    }

    @Test
    public void filterBy_Should_Return_StatusOk_When_FirstNamePassed() throws Exception {

        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(mockService.get("Test", null)).thenReturn(employees);

        //Act, Assert
        mockMvc.perform(
                get("/api/employees?firstName=test"))
                .andExpect(status().isOk());

    }

    @Test
    public void filterBy_Should_Return_StatusOk_When_LastNamePassed() throws Exception {

        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(mockService.get(null, "Employee")).thenReturn(employees);

        //Act, Assert
        mockMvc.perform(
                get("/api/employees?lastName=employee"))
                .andExpect(status().isOk());

    }
}
