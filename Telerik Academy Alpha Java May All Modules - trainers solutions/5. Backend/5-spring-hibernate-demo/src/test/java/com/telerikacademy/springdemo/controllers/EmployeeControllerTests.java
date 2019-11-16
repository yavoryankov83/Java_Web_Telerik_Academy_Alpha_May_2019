package com.telerikacademy.springdemo.controllers;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {
    @MockBean
    EmployeeService mockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getById_Should_Return_StatusOK_When_EmployeeExist() throws Exception {
        // Arrange
        Mockito.when(mockService.getById(1))
                .thenReturn(new Employee(1, "FirstName", "LastName"));

        // Act, Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("FirstName"));
    }

    @Test
    public void getById_Should_Return_StatusNOTFOUND_When_EmployeeNotExist() throws Exception {
        // Arrange
        Mockito.when(mockService.getById(1))
                .thenThrow(IllegalArgumentException.class);

        // Act, Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
