package com.employeeproject.controllers;

import com.employeeproject.exceptions.NotFoundException;
import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.employeeproject.services.contracts.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTests {

  @MockBean
  ProjectService mockedProjectService;

  @Autowired
  MockMvc mockMvc;

  private Map<Integer, Project> projects;
  private Employee employee;
  private Project project;

  @Before
  public void initialize() {
    projects = new HashMap<>();
    employee = new Employee(1, "Ivan", "Todorov");
    project = new Project(1, "Java");
    projects.put(1, project);

    Mockito.when(mockedProjectService.getAllProjects()).thenReturn(projects);
    Mockito.when(mockedProjectService.getProjectById(project.getId())).thenReturn(project);
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void getAll_Should_Return_StatusCode_OK_When_Call() throws Exception {
    //Arrange

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects"))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedProjectService, Mockito.times(1)).getAllProjects();
    Mockito.verifyNoMoreInteractions(mockedProjectService);
  }

  @Test
  public void getById_Should_Return_StatusCode_OK_When_Id_Exist() throws Exception {
    //Arrange

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/{projectId}", project.getId()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Java")));

    Mockito.verify(mockedProjectService, Mockito.times(1)).getProjectById(project.getId());
    Mockito.verifyNoMoreInteractions(mockedProjectService);
  }

  @Test
  public void getById_Should_Return_StatusCode_NOT_FOUND_When_Id_Not_Exist() throws Exception {
    //Arrange
    int invalidId = 47;
    Mockito.when(mockedProjectService.getProjectById(invalidId)).thenThrow(NotFoundException.class);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/{id}", invalidId))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedProjectService, Mockito.times(1)).getProjectById(invalidId);
    Mockito.verifyNoMoreInteractions(mockedProjectService);
  }

  @Test
  public void filter_Should_Return_StatusCode_OK_When_Filtered_By_Given_Parameter() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();
    map.put("name", "Java");

    List<Project> projectList = new ArrayList<>();
    projectList.add(project);

    Mockito.when(mockedProjectService.projectFilter(map)).thenReturn(projectList);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/filtered?name=Ivan"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void filter_Should_Return_EmptyList_When_Filtered_By_No_Parameters() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();

    Mockito.when(mockedProjectService.projectFilter(map)).thenReturn(Collections.emptyList());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/filtered"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void create_Should_Call_Create_Method_1_Time_When_Call() throws Exception {
    //Arrange
    Mockito.doNothing().when(mockedProjectService).addProject(project);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/projects")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(project)))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void create_Should_Return_StatusCode_Is4xx_When_Give_No_Body() throws Exception {
    //Arrange

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/projects")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    Mockito.verifyNoMoreInteractions(mockedProjectService);
  }

  @Test
  public void delete_Should_Call_Delete_Method_1_Time_When_Call() throws Exception {
    //Arrange
    Mockito.doNothing().when(mockedProjectService).deleteProject(project.getId());

    //Act
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/projects/{id}", project.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk());

    //Assert
    Mockito.verify(mockedProjectService, Mockito.times(1)).deleteProject(project.getId());
    Mockito.verifyNoMoreInteractions(mockedProjectService);
  }

  @Test
  public void delete_Should_Return_StatusCode_NOT_FOUND_When_Id_Not_Exist() throws Exception {
    //Arrange
    Mockito.when(mockedProjectService.getProjectById(project.getId())).thenReturn(null);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.delete("/projects/{id}", project.getId()))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void update_Should_Return_StatusCode_NOT_FOUND_When_Id_Not_Exist() throws Exception {
    //Arrange
    Mockito.when(mockedProjectService.getProjectById(project.getId())).thenReturn(null);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/projects/{id}", project.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(project)))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void getAllById_Should_Return_StatusCode_OK_When_Filtered_By_Given_Parameters() throws Exception {
    //Arrange
    List<Integer> projectListId = new ArrayList<>();
    List<Project> projectList = new ArrayList<>();
    projectList.add(project);

    projectListId.add(project.getId());
    Mockito.when(mockedProjectService.projectFilterByMultipleId(projectListId)).thenReturn(projectList);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/id_filter?id=1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getAllById_Should_Return_EmptyList_When_Filtered_By_No_Parameters() throws Exception {
    //Arrange
    List<Integer> projectListId = new ArrayList<>();
    projectListId.add(3);

    Mockito.when(mockedProjectService.projectFilterByMultipleId(projectListId))
            .thenReturn(Collections.emptyList());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/id_filter?id=3"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void sort_Should_Return_StatusCode_OK_When_Sort_By_Given_Parameter() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();
    map.put("sort", "name_asc");

    List<Project> projectList = new ArrayList<>();
    projectList.add(project);

    Mockito.when(mockedProjectService.projectSort(map)).thenReturn(projectList);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/sorted/?sort=name_asc"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void sort_Should_Return_EmptyList_When_Filtered_By_No_Parameters() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();

    Mockito.when(mockedProjectService.projectSort(map)).thenReturn(Collections.emptyList());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/sorted"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getEmployees_Should_Return_StatusCode_OK_When_Id_Exist() throws Exception {
    //Arrange
    Map<Integer, Employee> employeeMap = new HashMap<>();
    employeeMap.put(employee.getId(), employee);

    Mockito.when(mockedProjectService.getAllEmployeesOfProject(project.getId())).thenReturn(employeeMap);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/1/employees"))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedProjectService, Mockito.times(1))
            .getAllEmployeesOfProject(project.getId());
  }

  @Test
  public void getEmployees_Should_Return_EmptyMap_When_Id_Has_No_Projects() throws Exception {
    //Arrange
    Mockito.when(mockedProjectService.getAllEmployeesOfProject(project.getId()))
            .thenReturn(Collections.emptyMap());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/projects/1/employees"))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedProjectService, Mockito.times(1))
            .getAllEmployeesOfProject(project.getId());
  }

  @Test
  public void assign_Should_Return_StatusCode_Is4xx_When_ProjectId_Is_Used() throws Exception {
    //Arrange
    Mockito.when(mockedProjectService.isEmployeeAssignedToProject(project.getId(), employee.getId()))
            .thenReturn(true);
    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/projects/{projectId}/assign_employee/{employeeId}",
            project.getId(), null))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void assign_Should_Return_StatusCode_OK_When_ProjectId_Is_Not_Used() throws Exception {
    //Arrange
    Mockito.when(mockedProjectService.isEmployeeAssignedToProject(project.getId(), employee.getId()))
            .thenReturn(false);
    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/projects/{projectId}/assign_employee/{employeeId}",
            project.getId(), employee.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedProjectService, Mockito.times(1))
            .assignEmployeeToProject(project.getId(), employee.getId());
  }

  @Test
  public void unAssign_Should_Return_StatusCode_Is4xx_When_EmployeeId_Is_Used() throws Exception {
    //Arrange
    Mockito.when(mockedProjectService.isEmployeeAssignedToProject(project.getId(), employee.getId()))
            .thenReturn(false);
    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/projects/{projectId}/unAssign_employee/{employeeId}",
            project.getId(), null))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void unAssign_Should_Return_StatusCode_OK_When_EmployeeId_Is_Not_Used() throws Exception {
    //Arrange
    Mockito.when(mockedProjectService.isEmployeeAssignedToProject(project.getId(), employee.getId()))
            .thenReturn(true);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/projects/{projectId}/unAssign_employee/{employeeId}",
            project.getId(), employee.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedProjectService, Mockito.times(1))
            .usAssignEmployeeFromProject(project.getId(), employee.getId());
  }

  @After
  public void unInitialize() {
    projects = null;
  }
}
