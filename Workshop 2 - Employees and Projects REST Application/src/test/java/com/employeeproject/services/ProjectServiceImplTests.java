package com.employeeproject.services;

import com.employeeproject.exceptions.ConflictException;
import com.employeeproject.exceptions.NotFoundException;
import com.employeeproject.models.Employee;
import com.employeeproject.models.Project;
import com.employeeproject.repositories.contracts.ProjectRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTests {

  @Mock
  ProjectRepository mockProjectRepository;

  @InjectMocks
  ProjectServiceImpl projectService;

  private Map<Integer, Project> projects;
  private Employee employee;
  private Project project;

  @Before
  public void initialize() {
    projects = new HashMap<>();
    projects.put(1, new Project(1, "Spring MVC"));
    projects.put(2, new Project(2, "Java"));
    projects.put(3, new Project(3, "Database"));
    projects.put(4, new Project(4, "WEB"));
    projects.put(5, new Project(5, "WEB API"));

    employee = new Employee(6, "Ivana", "Ivana");
    project = new Project(6, "Java");

    Mockito.when(mockProjectRepository.getAllProjects()).thenReturn(projects);
  }

  @Test
  public void getAllProjects_Should_Return_Projects_Count_When_Exist() {
    //Arrange

    //Act
    int size = projectService.getAllProjects().size();

    //Assert
    Assert.assertEquals(5, size);
  }

  @Test
  public void getAllProjects_Should_Return_Projects_Count_Equals_0_When_No_Projects_Exist() {
    //Arrange
    projects.clear();
    //Act
    int size = projectService.getAllProjects().size();

    //Assert
    Assert.assertEquals(0, size);
  }

  @Test
  public void getProjectsById_Should_Return_Project_Id_When_Project_Exist() {
    //Arrange

    //Act
    Project projectById = projectService.getAllProjects().get(1);

    int expectedId = 1;
    int actualId = projectById.getId();

    //Assert
    Assert.assertEquals(expectedId, actualId);
  }

  @Test(expected = NotFoundException.class)
  public void getProjectById_Should_Throw_NotFoundException_When_Project_Not_Exist() {
    //Arrange
    int invalidId = 45;

    //Act
    projectService.getProjectById(invalidId);


    //Assert
  }

  @Test
  public void addProject_Should_Invoke_AddProject_Method_1_Time_When_Project_Not_Exist_Yet() {
    //Arrange

    //Act
    projectService.addProject(project);

    //Assert
    Mockito.verify(mockProjectRepository, Mockito.times(1)).addProject(project);
  }

  @Test(expected = ConflictException.class)
  public void addProject_Should_Throw_ConflictException_When_Project__Exist_Already() {
    //Arrange
    Mockito.doThrow(ConflictException.class).when(mockProjectRepository).addProject(Mockito.isA(Project.class));

    //Act
    projectService.addProject(project);

    //Assert
  }

  @Test(expected = NotFoundException.class)
  public void deleteProject_Should_Throw_NotFoundException_When_Project_Not_Exist() {
    //Arrange

    //Act
    projectService.deleteProject(project.getId());

    //Assert
  }

  @Test
  public void getProjectEmployees_Should_Return_ProjectEmployees_Count_When_Call() {
    //Arrange
    Map<Integer, Map<Integer, Employee>> map = new HashMap<>();
    map.put(employee.getId(), new HashMap<>());
    map.get(employee.getId()).put(project.getId(), employee);

    Mockito.when(mockProjectRepository.getAllEmployeesOfProject(project.getId())).thenReturn(map.get(employee.getId()));

    //Act
    Map<Integer, Employee> allEmployeeProjects = mockProjectRepository.getAllEmployeesOfProject(project.getId());
    int expectedSize = 1;
    int actualSize = allEmployeeProjects.size();

    //Assert
    Assert.assertEquals(expectedSize, actualSize);
  }

  @Test
  public void projectFilter_Should_Return_Project_With_Name_When_Exist() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("name", "Java");

    //Act
    List<Project> projects = projectService.projectFilter(employeeMap);

    //Assert
    Assert.assertEquals(1, projects.size());
  }

  @Test
  public void projectFilter_Should_Return_EmptyList_When_Project_Not_Exist() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("name", "HTML 5");

    //Act
    List<Project> projects = projectService.projectFilter(employeeMap);

    //Assert
    Assert.assertEquals(0, projects.size());
  }

  @Test
  public void projectSort_Should_Return_Project_Sorted_By_Name_Asc_When_Sort_By_Name_Asc() {
    //Arrange
    Map<String, String> projectMap = new HashMap<>();
    projectMap.put("sort", "name_asc");
    String nameToExpect = "Database";

    //Act
    List<Project> projects = projectService.projectSort(projectMap);
    String actualName = projects.get(0).getName();

    //Assert
    Assert.assertEquals(nameToExpect, actualName);
  }

  @Test
  public void projectSort_Should_Return_Project_Sorted_By_Name_Desc_When_Sort_By_Name_Desc() {
    //Arrange
    Map<String, String> projectMap = new HashMap<>();
    projectMap.put("sort", "name_desc");
    String nameToExpect = "WEB API";

    //Act
    List<Project> projects = projectService.projectSort(projectMap);
    String actualName = projects.get(0).getName();

    //Assert
    Assert.assertEquals(nameToExpect, actualName);
  }

  @Test
  public void projectSort_Should_Return_EmptyListWhen_Project_Not_Exist() {
    //Arrange
    Mockito.when(mockProjectRepository.getAllProjects()).thenReturn(Collections.emptyMap());
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("sort", "name_desc");

    //Act
    List<Project> projects = projectService.projectSort(employeeMap);
    int expectedSize = 0;
    int actualSize = projects.size();
    //Assert
    Assert.assertEquals(expectedSize, actualSize);
  }

  @After
  public void unInitialize() {
    projects = null;
  }
}
