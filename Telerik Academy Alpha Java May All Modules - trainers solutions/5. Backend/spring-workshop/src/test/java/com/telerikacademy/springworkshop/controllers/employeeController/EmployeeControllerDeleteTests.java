package com.telerikacademy.springworkshop.controllers.employeeController;

import com.telerikacademy.springworkshop.services.contracts.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerDeleteTests {

    @MockBean
    EmployeeService mockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void delete_Should_Return_StatusNotFound_When_EmployeeDoesntExists() throws Exception {

        //Arrange
        doThrow(new RuntimeException()).when(mockService).delete(1);

        //Act, Assert
        mockMvc.perform(
                delete("/api/employees/1"))
                .andExpect(status().isNotFound());
    }
}