package com.telerikacademy.springdemo.models;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Employee {
    @PositiveOrZero
    private int id;

    @Size(min = 2, max = 20)
    private String firstName;

    @Size(min = 2, max = 20)
    private String lastName;

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
}
