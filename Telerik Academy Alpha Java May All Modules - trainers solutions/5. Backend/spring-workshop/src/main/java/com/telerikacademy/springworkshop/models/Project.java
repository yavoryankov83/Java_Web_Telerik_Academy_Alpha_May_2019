package com.telerikacademy.springworkshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Project {

    @PositiveOrZero
    private int id;

    @Size(min = 3, max = 15, message = "Project name should be between 3 and 15 symbols.")
    private String name;

    @JsonIgnore
    private List<Employee> employees;

    public Project() {
        this(0, "");
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
        employees = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
