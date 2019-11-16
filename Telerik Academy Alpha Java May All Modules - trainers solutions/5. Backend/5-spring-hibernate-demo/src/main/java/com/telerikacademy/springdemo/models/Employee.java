package com.telerikacademy.springdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @PositiveOrZero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private int id;

    @Size(min = 2, max = 20)
    @Column(name = "FirstName")
    private String firstName;

    @Size(min = 2, max = 20)
    @Column(name = "LastName")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "AddressID")
    private Address address;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "employeesprojects",
            joinColumns = @JoinColumn(name = "EmployeeID"),
            inverseJoinColumns = @JoinColumn(name = "ProjectID")
    )
    private List<Project> projects;

    public Employee() {
        this(0, "", "");
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
