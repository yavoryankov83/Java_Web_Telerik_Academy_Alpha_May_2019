package com.telerikacademy.springworkshop.controllers.projectController;

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
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerUpdateTests {

    @MockBean
    ProjectService mockService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void update_Should_Return_StatusOk_When_ProjectIsValid() throws Exception {

        //Arrange
        Project project = new Project(1, "project");

        //Act, Assert
        mockMvc.perform(put("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isOk());
    }

    @Test
    public void update_Should_Return_StatusBadRequest_When_ProjectIdIsNegative() throws Exception {

        //Arrange
        Project project = new Project(-1, "project");

        //Act, Assert
        mockMvc.perform(put("/api/projects/-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_Should_Return_StatusBadRequest_When_ProjectNameIsShort() throws Exception {

        //Arrange
        Project project = new Project(1, new String(new char[2]));

        //Act, Assert
        mockMvc.perform(put("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_Should_Return_StatusBadRequest_When_ProjectNameIsLong() throws Exception {

        //Arrange
        Project project = new Project(1, new String(new char[16]));

        //Act, Assert
        mockMvc.perform(put("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_Should_Return_StatusBadRequest_When_ProjectDoesNotExist() throws Exception {
        //Arrange
        doThrow(RuntimeException.class).when(mockService).update(anyInt(), any(Project.class));
        Project project = new Project(1, "project");

        //Act, Assert
        mockMvc.perform(put("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(project)))
                .andExpect(status().isBadRequest());
    }


}
