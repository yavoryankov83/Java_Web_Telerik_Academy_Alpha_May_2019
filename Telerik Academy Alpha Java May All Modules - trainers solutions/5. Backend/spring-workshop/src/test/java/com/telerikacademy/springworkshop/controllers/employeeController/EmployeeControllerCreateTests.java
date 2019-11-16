package com.telerikacademy.springworkshop.controllers.employeeController;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.repositories.contracts.EmployeeRepository;
import com.telerikacademy.springworkshop.services.contracts.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.telerikacademy.springworkshop.controllers.JsonHelper.convertObjectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerCreateTests {

    @MockBean
    EmployeeService mockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void create_Should_Return_StatusOk_When_EmployeeIsValid() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");

        //Act
        mockMvc.perform(
                post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(employee)))
                .andExpect(status().isOk());

    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_EmployeeIdIsNegative() throws Exception {
        //Arrange
        Employee employee = new Employee(-1, "Test", "Employee");

        //Act
        mockMvc.perform(
                post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(employee)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_EmployeeFirstNameIsTooShort() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "T", "Employee");

        //Act
        mockMvc.perform(
                post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(employee)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_EmployeeLastNameIsTooShort() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "Test", "E");

        //Act
        mockMvc.perform(
                post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(employee)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_EmployeeFirstNameIsTooLong() throws Exception {
        //Arrange
        Employee employee = new Employee(1, new String(new char[21]), "Employee");

        //Act
        mockMvc.perform(
                post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(employee)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_EmployeeLastNameIsTooLong() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "Test", new String(new char[21]));

        //Act
        mockMvc.perform(
                post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(employee)))
                .andExpect(status().isBadRequest());
    }
}
