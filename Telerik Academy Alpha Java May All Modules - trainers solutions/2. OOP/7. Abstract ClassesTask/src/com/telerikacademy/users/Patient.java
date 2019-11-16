package com.telerikacademy.users;

import com.telerikacademy.insurance.HealthInsurancePlan;

public class Patient extends User {

    public Patient(long id, String firstName, String lastName, String gender, String email) {
        super(id, firstName, lastName, gender, email);
    }
}
