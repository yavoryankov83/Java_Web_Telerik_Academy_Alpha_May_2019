package com.telerikacademy.springworkshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    @PositiveOrZero
    private int id;

    @Size(min = 3, max = 20, message = "Employee first name should be between 3 and 20 symbols.")
    private String firstName;

    @Size(min = 3, max = 20, message = "Employee last name should be between 3 and 20 symbols.")
    private String lastName;

    @JsonIgnore
    private List<Project> projects;

    public Employee() {
        this(0, "", "");
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        projects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
