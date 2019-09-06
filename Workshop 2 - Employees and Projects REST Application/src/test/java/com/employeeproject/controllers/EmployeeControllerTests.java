package com.employeeproject.controllers;

import com.employeeproject.exceptions.NotFoundException;
import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.employeeproject.services.contracts.EmployeeService;
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

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {

  @MockBean
  EmployeeService mockedEmployeeService;

  @Autowired
  MockMvc mockMvc;

  private Map<Integer, Employee> employees;
  private Employee employee;
  private Project project;

  @Before
  public void initialize() {
    employees = new HashMap<>();
    employee = new Employee(1, "Ivan", "Todorov");
    project = new Project(1, "Java");
    employees.put(1, employee);
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
    Mockito.when(mockedEmployeeService.getAllEmployees()).thenReturn(employees);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedEmployeeService, Mockito.times(1)).getAllEmployees();
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void getById_Should_Return_StatusCode_OK_When_Id_Exist() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(employee);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/{id}", 1))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("Ivan")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Todorov")));

    Mockito.verify(mockedEmployeeService, Mockito.times(1)).getEmployeeById(employee.getId());
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void getById_Should_Return_StatusCode_NOT_FOUND_When_Id_Not_Exist() throws Exception {
    //Arrange
    int invalidId = 47;
    Mockito.when(mockedEmployeeService.getEmployeeById(invalidId)).thenThrow(NotFoundException.class);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/{id}", invalidId))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedEmployeeService, Mockito.times(1)).getEmployeeById(invalidId);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void filter_Should_Return_StatusCode_OK_When_Filtered_By_Given_Parameter() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();
    map.put("firstName", "Ivan");

    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(employee);

    Mockito.when(mockedEmployeeService.employeeFilter(map)).thenReturn(employeeList);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/filtered?firstName=Ivan"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void filter_Should_Return_EmptyList_When_Filtered_By_No_Parameters() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();

    Mockito.when(mockedEmployeeService.employeeFilter(map)).thenReturn(Collections.emptyList());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/filtered"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void create_Should_Return_StatusCode_OK_When_Employee_Is_Valid() throws Exception {
    //Arrange
    Mockito.doNothing().when(mockedEmployeeService).addEmployee(employee);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void create_Should_Return_StatusCode_BADREQUEST_When_Employee_Id_Is_Negative() throws Exception {
    //Arrange
    employee.setId(-1);
    Mockito.doNothing().when(mockedEmployeeService).addEmployee(employee);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void create_Should_Return_StatusCode_BADREQUEST_When_Employee_FirstName_Is_Too_Short() throws Exception {
    //Arrange
    employee.setFirstName("T");
    Mockito.doNothing().when(mockedEmployeeService).addEmployee(employee);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).addEmployee(employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void create_Should_Return_StatusCode_BADREQUEST_When_Employee_LastName_Is_Too_Short() throws Exception {
    //Arrange
    employee.setLastName("T");
    Mockito.doNothing().when(mockedEmployeeService).addEmployee(employee);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).addEmployee(employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void create_Should_Return_StatusCode_BADREQUEST_When_Employee_FirstName_Is_Too_Long() throws Exception {
    //Arrange
    employee.setFirstName(new String(new char[58]));
    Mockito.doNothing().when(mockedEmployeeService).addEmployee(employee);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).addEmployee(employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void create_Should_Return_StatusCode_BADREQUEST_When_Employee_LastName_Is_Too_Long() throws Exception {
    //Arrange
    employee.setLastName(new String(new char[89]));
    Mockito.doNothing().when(mockedEmployeeService).addEmployee(employee);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).addEmployee(employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void create_Should_Return_StatusCode_Is4xx_When_Give_No_Body() throws Exception {
    //Arrange
    Mockito.doNothing().when(mockedEmployeeService).addEmployee(employee);
    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).addEmployee(employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void delete_Should_Call_Delete_Method_1_Time_When_Employee_Exist() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(employee);
    Mockito.doNothing().when(mockedEmployeeService).deleteEmployee(employee.getId());

    //Act
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employees/{id}", employee.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk());

    //Assert
    Mockito.verify(mockedEmployeeService, Mockito.times(1)).deleteEmployee(employee.getId());
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void delete_Should_Return_StatusCode_NOT_FOUND_When_Id_Not_Exist() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(null);
    Mockito.doThrow(NotFoundException.class).when(mockedEmployeeService).deleteEmployee(employee.getId());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", employee.getId()))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).deleteEmployee(employee.getId());
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void update_Should_Return_StatusCode_NOT_FOUND_When_Id_Not_Exist() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(null);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).updateEmployee(employee.getId(), employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void update_Should_Return_StatusCode_BADREQUEST_When_Employee_LastName_Is_Too_Long() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(employee);
    employee.setLastName(new String(new char[87]));

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).updateEmployee(employee.getId(), employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void update_Should_Return_StatusCode_BADREQUEST_When_Employee_FirstName_Is_Too_Long() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(employee);
    employee.setFirstName(new String(new char[87]));

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).updateEmployee(employee.getId(), employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void update_Should_Return_StatusCode_BADREQUEST_When_Employee_FirstName_Is_Too_Short() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(employee);
    employee.setFirstName("Iv");

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).updateEmployee(employee.getId(), employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void update_Should_Return_StatusCode_BADREQUEST_When_Employee_LastName_Is_Too_Short() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getEmployeeById(employee.getId())).thenReturn(employee);
    employee.setLastName("Iv");

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(employee)))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

    Mockito.verify(mockedEmployeeService, Mockito.times(0)).updateEmployee(employee.getId(), employee);
    Mockito.verifyNoMoreInteractions(mockedEmployeeService);
  }

  @Test
  public void getAllById_Should_Return_StatusCode_OK_When_Filtered_By_Given_Parameters() throws Exception {
    //Arrange
    List<Integer> employeeListId = new ArrayList<>();
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(employee);

    employeeListId.add(employee.getId());
    Mockito.when(mockedEmployeeService.employeeFilterByMultipleId(employeeListId)).thenReturn(employeeList);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/id_filter?id=1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getAllById_Should_Return_EmptyList_When_Filtered_By_No_Parameters() throws Exception {
    //Arrange
    List<Integer> employeeListId = new ArrayList<>();
    employeeListId.add(3);

    Mockito.when(mockedEmployeeService.employeeFilterByMultipleId(employeeListId)).thenReturn(Collections.emptyList());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/id_filter?id=3"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void sort_Should_Return_StatusCode_OK_When_Sort_By_Given_Parameter() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();
    map.put("sort", "firstName_asc");

    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(employee);

    Mockito.when(mockedEmployeeService.employeeSort(map)).thenReturn(employeeList);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/sorted/?sort=firstName_asc"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void sort_Should_Return_EmptyList_When_Filtered_By_No_Parameters() throws Exception {
    //Arrange
    Map<String, String> map = new HashMap<>();

    Mockito.when(mockedEmployeeService.employeeSort(map)).thenReturn(Collections.emptyList());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/sorted"))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getProjects_Should_Return_StatusCode_OK_When_Id_Exist() throws Exception {
    //Arrange
    Map<Integer, Project> projectMap = new HashMap<>();
    projectMap.put(project.getId(), project);

    Mockito.when(mockedEmployeeService.getAllProjectsOfEmployee(employee.getId())).thenReturn(projectMap);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1/projects"))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedEmployeeService, Mockito.times(1)).getAllProjectsOfEmployee(employee.getId());
  }

  @Test
  public void getProjects_Should_Return_EmptyMap_When_Id_Has_No_Projects() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.getAllProjectsOfEmployee(employee.getId()))
            .thenReturn(Collections.emptyMap());

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1/projects"))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedEmployeeService, Mockito.times(1))
            .getAllProjectsOfEmployee(employee.getId());
  }

  @Test
  public void assign_Should_Return_StatusCode_Is4xx_When_EmployeeId_Is_Used() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.isProjectAssignedToEmployee(employee.getId(), project.getId())).thenReturn(true);
    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/{employeeId}/assign_project/{projectId}",
            null, project.getId()))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void assign_Should_Return_StatusCode_OK_When_EmployeeId_Is_Not_Used() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.isProjectAssignedToEmployee(employee.getId(), project.getId())).thenReturn(false);
    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/{employeeId}/assign_project/{projectId}",
            employee.getId(), project.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedEmployeeService, Mockito.times(1))
            .assignProjectToEmployee(employee.getId(), project.getId());
  }

  @Test
  public void unAssign_Should_Return_StatusCode_Is4xx_When_EmployeeId_Is_Used() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.isProjectAssignedToEmployee(employee.getId(), project.getId()))
            .thenReturn(false);
    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/{employeeId}/unAssign_project/{projectId}",
            null, project.getId()))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void unAssign_Should_Return_StatusCode_OK_When_EmployeeId_Is_Not_Used() throws Exception {
    //Arrange
    Mockito.when(mockedEmployeeService.isProjectAssignedToEmployee(employee.getId(), project.getId()))
            .thenReturn(true);

    //Act, Assert
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/{employeeId}/unAssign_project/{projectId}",
            employee.getId(), project.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(mockedEmployeeService, Mockito.times(1))
            .usAssignProjectFromEmployee(employee.getId(), project.getId());
  }

  @After
  public void unInitialize() {
    employees = null;
  }
}
