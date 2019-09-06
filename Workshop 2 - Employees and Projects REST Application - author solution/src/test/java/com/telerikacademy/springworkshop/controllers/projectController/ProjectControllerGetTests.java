package com.telerikacademy.springworkshop.controllers.projectController;

import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.services.contracts.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerGetTests {

    @MockBean
    ProjectService mockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getById_Should_Return_StatusOK_When_ProjectExist() throws Exception {

        // Arrange
        Mockito.when(mockService.getById(1))
                .thenReturn(new Project(1, "project"));

        // Act, Assert
        mockMvc.perform(get("/api/projects/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("project"));
    }

    @Test
    public void getById_Should_Return_StatusNotFound_When_ProjectDoesNotExist() throws Exception {
        //Arrange
        doThrow(RuntimeException.class).when(mockService).getById(anyInt());

        //Act, Assert
        mockMvc.perform(get("/api/projects/{id}", 1))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getAll_Should_Return_StatusOk() throws Exception {

        //Arrange, Act, Assert
        mockMvc.perform(
                get("/api/projects"))
                .andExpect(status().isOk());

    }


    @Test
    public void getEmployees_Should_ReturnNotFound_When_ProjectDoesNotExist() throws Exception {

        //Arrange
        doThrow(new RuntimeException()).when(mockService).getById(1);

        //Act, Assert
        mockMvc.perform(get("/api/projects/1/employees"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmployees_Should_ReturnOk_When_ProjectExists() throws Exception {

        //Arrange
        when(mockService.getById(anyInt())).thenReturn(new Project());

        //Act, Assert
        mockMvc.perform(get("/api/projects/1/employees"))
                .andExpect(status().isOk());
    }
}
