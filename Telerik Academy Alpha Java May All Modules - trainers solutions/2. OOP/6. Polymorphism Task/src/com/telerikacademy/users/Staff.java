package com.telerikacademy.users;

public class Staff extends User {
    private int yearsOfExperience;
    private String description;
    private double salary;

    public Staff(long id, String firstName, String lastName, String gender, String email, int yearsOfExperience, String description, double salary) {
        super(id, firstName, lastName, gender, email);
        this.yearsOfExperience = yearsOfExperience;
        this.description = description;
        this.salary = salary;
    }
}
