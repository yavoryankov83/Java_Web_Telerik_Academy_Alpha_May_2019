package com.telerikacademy.users;

public class Nurse extends Staff {
    public Nurse(long id, String firstName, String lastName, String gender, String email, int yearsOfExperience, String description, double salary) {
        super(id, firstName, lastName, gender, email, yearsOfExperience, description, salary);
    }
}
