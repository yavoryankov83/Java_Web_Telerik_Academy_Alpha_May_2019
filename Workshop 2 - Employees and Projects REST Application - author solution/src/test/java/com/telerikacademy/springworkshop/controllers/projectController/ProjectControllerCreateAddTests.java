package com.telerikacademy.springworkshop.controllers.projectController;

import com.telerikacademy.springworkshop.models.Employee;
import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.services.contracts.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.telerikacademy.springworkshop.controllers.JsonHelper.convertObjectToJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerCreateAddTests {

    @MockBean
    ProjectService mockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void create_Should_Return_StatusOk_When_ProjectIsValid() throws Exception {

        //Arrange
        Project project = new Project(1, "project");

        //Act, Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isOk());
    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_ProjectIdIsNegative() throws Exception {

        //Arrange
        Project project = new Project(-1, "project");

        //Act, Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_ProjectNameIsShort() throws Exception {

        //Arrange
        Project project = new Project(1, new String(new char[2]));

        //Act, Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_Should_Return_StatusBadRequest_When_ProjectNameIsLong() throws Exception {

        //Arrange
        Project project = new Project(1, new String(new char[16]));

        //Act, Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_Should_Return_StatusConflict_When_ProjectExist() throws Exception {

        //Arrange
        Project project = new Project(1, "project");

        doThrow(RuntimeException.class).when(mockService).create(any(Project.class));

        //Act, Assert
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isConflict());
    }

    @Test
    public void addEmployee_Should_ReturnNotFound_When_ProjectDoesNotExist() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");

        doThrow(RuntimeException.class)
                .when(mockService).addEmployee(anyInt(), any(Employee.class));

        //Act, Assert
        mockMvc.perform(put("/api/projects/1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(employee)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void addEmployee_Should_ReturnNotFound_When_ProjectExists() throws Exception {

        //Arrange
        Employee employee = new Employee(1, "Test", "Employee");
        doNothing().when(mockService).addEmployee(anyInt(), any(Employee.class));

        //Act, Assert
        mockMvc.perform(put("/api/projects/1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(employee)))
                .andExpect(status().isOk());
    }

}
