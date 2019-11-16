package com.telerikacademy.springworkshop.controllers.projectController;

import com.telerikacademy.springworkshop.services.contracts.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerDeleteRemoveTests {

    @MockBean
    ProjectService mockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void delete_Should_ReturnNotFound_When_ProjectDoesNotExist() throws Exception {

        //Arrange
        doThrow(new RuntimeException()).when(mockService).delete(1);

        //Act, Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/projects/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_Should_ReturnOk_When_ProjectExists() throws Exception {

        //Arrange, Act, Assert
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/projects/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void removeEmployee_Should_Return_StatusNotFound_When_EmployeeDoesNotExist() throws Exception {

        //Arrange
        doThrow(RuntimeException.class).when(mockService).removeEmployee(anyInt(), anyInt());

        //Act, Assert
        mockMvc.perform(delete("/api/projects/1/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void removeEmployee_Should_Return_StatusNotFound_When_ProjectDoesNotExist() throws Exception {

        //Arrange
        doThrow(RuntimeException.class).when(mockService).removeEmployee(anyInt(), anyInt());

        //Act, Assert
        mockMvc.perform(delete("/api/projects/1/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void removeEmployee_Should_Return_StatusOk_When_EmployeeAndProjectExist() throws Exception {

        //Arrange
        doNothing().when(mockService).removeEmployee(anyInt(), anyInt());

        //Act, Assert
        mockMvc.perform(delete("/api/projects/1/employees/1"))
                .andExpect(status().isOk());
    }

}
