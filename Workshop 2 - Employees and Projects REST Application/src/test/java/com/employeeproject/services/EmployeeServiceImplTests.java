package com.employeeproject.services;

import com.employeeproject.exceptions.ConflictException;
import com.employeeproject.exceptions.NotFoundException;
import com.employeeproject.models.EmployeeImpl;
import com.employeeproject.models.ProjectImpl;
import com.employeeproject.repositories.contracts.EmployeeRepository;
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
public class EmployeeServiceImplTests {
  @Mock
  EmployeeRepository mockRepository;

  @InjectMocks
  EmployeeServiceImpl employeeService;

  private Map<Integer, EmployeeImpl> employees;
  private EmployeeImpl employee;
  private ProjectImpl project;

  @Before
  public void initialize() {
    employees = new HashMap<>();
    employees.put(1, new EmployeeImpl(1, "Ivan", "Todorov"));
    employees.put(2, new EmployeeImpl(2, "Penka", "Minkova"));
    employees.put(3, new EmployeeImpl(3, "Ralica", "Stoqnova"));
    employees.put(4, new EmployeeImpl(4, "Rosen", "Rosenov"));
    employees.put(5, new EmployeeImpl(5, "Rosen", "Yankov"));

    employee = new EmployeeImpl(6, "Ivana", "Ivana");
    project = new ProjectImpl(6, "Java");

    Mockito.when(mockRepository.getAllEmployees()).thenReturn(employees);
  }

  @Test
  public void getAllEmployees_Should_Return_Employees_Count_When_Exist() {
    //Arrange

    //Act
    int size = employeeService.getAllEmployees().size();

    //Assert
    Assert.assertEquals(5, size);
  }

  @Test
  public void getAllEmployees_Should_Return_Employees_Count_Equals_0_When_No_Employees_Exist() {
    //Arrange
    employees.clear();
    //Act
    int size = employeeService.getAllEmployees().size();

    //Assert
    Assert.assertEquals(0, size);
  }

  @Test
  public void getEmployeeById_Should_Return_Employee_Id_When_Employee_Exist() {
    //Arrange

    //Act
    EmployeeImpl employee = employeeService.getAllEmployees().get(1);

    //Assert
    Assert.assertEquals(1, employee.getId());
  }

  @Test(expected = NotFoundException.class)
  public void getEmployeeById_Should_Throw_NotFoundException_When_Employee_Not_Exist() {
    //Arrange

    //Act
    employeeService.getEmployeeById(7);

    //Assert
  }

  @Test
  public void addEmployee_Should_Invoke_AddEmployee_Method_1_Time_When_Employee_Not_Exist_Yet() {
    //Arrange

    //Act
    employeeService.addEmployee(employee);

    //Assert
    Mockito.verify(mockRepository, Mockito.times(1)).addEmployee(employee);
  }

  @Test(expected = ConflictException.class)
  public void addEmployee_Should_Throw_ConflictException_When_Employee__Exist_Already() {
    //Arrange
    Mockito.doThrow(ConflictException.class).when(mockRepository).addEmployee(Mockito.isA(EmployeeImpl.class));

    //Act
    employeeService.addEmployee(employee);

    //Assert
  }

  @Test(expected = NotFoundException.class)
  public void deleteEmployee_Should_Throw_NotFoundException_When_Employee_Not_Exist() {
    //Arrange

    //Act
    employeeService.deleteEmployee(employee.getId());

    //Assert
  }

  @Test
  public void getEmployeeProjects_Should_Return_EmployeeProjects_Count_When_Call() {
    //Arrange
    Map<Integer, Map<Integer, ProjectImpl>> map = new HashMap<>();
    map.put(employee.getId(), new HashMap<>());
    map.get(employee.getId()).put(project.getId(), project);

    Mockito.when(mockRepository.getAllProjectsOfEmployee(employee.getId())).thenReturn(map.get(employee.getId()));

    //Act
    Map<Integer, ProjectImpl> allProjectsOfEmployee = mockRepository.getAllProjectsOfEmployee(employee.getId());
    int expectedSize = 1;
    int actualSize = allProjectsOfEmployee.size();

    //Assert
    Assert.assertEquals(expectedSize, actualSize);
  }

  @Test
  public void employeeFilter_Should_Return_Employee_With_FirstAndLastName_When_Exist() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("firstName", "Ivan");
    employeeMap.put("lastName", "Todorov");

    //Act
    List<EmployeeImpl> employees = employeeService.employeeFilter(employeeMap);

    //Assert
    Assert.assertEquals(1, employees.size());
  }

  @Test
  public void employeeFilter_Should_Return_Employee_With_FirstName_When_Exist() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("firstName", "Rosen");

    //Act
    List<EmployeeImpl> employees = employeeService.employeeFilter(employeeMap);

    //Assert
    Assert.assertEquals(2, employees.size());
  }

  @Test
  public void employeeFilter_Should_Return_Employee_With_LastName_When_Exist() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("lastName", "Yankov");

    //Act
    List<EmployeeImpl> employees = employeeService.employeeFilter(employeeMap);

    //Assert
    Assert.assertEquals(1, employees.size());
  }

  @Test
  public void employeeFilter_Should_Return_EmptyList_When_Employee_Not_Exist() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("lastName", "Gonzo");

    //Act
    List<EmployeeImpl> employees = employeeService.employeeFilter(employeeMap);

    //Assert
    Assert.assertEquals(0, employees.size());
  }

  @Test
  public void employeeSort_Should_Return_Employee_Sorted_By_FirstName_Asc_When_Sort_By_FirstName_Asc() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("sort", "firstName_asc");
    String nameToExpect = "Ivan";

    //Act
    List<EmployeeImpl> employees = employeeService.employeeSort(employeeMap);
    String actualName = employees.get(0).getFirstName();

    //Assert
    Assert.assertEquals(nameToExpect, actualName);
  }

  @Test
  public void employeeSort_Should_Return_Employee_Sorted_By_FirstName_Desc_When_Sort_By_FirstName_Desc() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("sort", "firstName_desc");
    String nameToExpect = "Rosen";

    //Act
    List<EmployeeImpl> employees = employeeService.employeeSort(employeeMap);
    String actualName = employees.get(0).getFirstName();

    //Assert
    Assert.assertEquals(nameToExpect, actualName);
  }

  @Test
  public void employeeSort_Should_Return_Employee_Sorted_By_LastName_Asc_When_Sort_By_LastName_Asc() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("sort", "lastName_asc");
    String nameToExpect = "Minkova";

    //Act
    List<EmployeeImpl> employees = employeeService.employeeSort(employeeMap);
    String actualName = employees.get(0).getLastName();

    //Assert
    Assert.assertEquals(nameToExpect, actualName);
  }

  @Test
  public void employeeSort_Should_Return_Employee_Sorted_By_LastName_Desc_When_Sort_By_LastName_Desc() {
    //Arrange
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("sort", "lastName_desc");
    String nameToExpect = "Yankov";

    //Act
    List<EmployeeImpl> employees = employeeService.employeeSort(employeeMap);
    String actualName = employees.get(0).getLastName();

    //Assert
    Assert.assertEquals(nameToExpect, actualName);
  }

  @Test
  public void employeeSort_Should_Return_EmptyListWhen_Employees_Not_Exist() {
    //Arrange
    Mockito.when(mockRepository.getAllEmployees()).thenReturn(Collections.emptyMap());
    Map<String, String> employeeMap = new HashMap<>();
    employeeMap.put("sort", "lastName_desc");

    //Act
    List<EmployeeImpl> employees = employeeService.employeeSort(employeeMap);
    int expectedSize = 0;
    int actualSize = employees.size();
    //Assert
    Assert.assertEquals(expectedSize, actualSize);
  }

  @After
  public void unInitialize() {
    employees = null;
  }
}
