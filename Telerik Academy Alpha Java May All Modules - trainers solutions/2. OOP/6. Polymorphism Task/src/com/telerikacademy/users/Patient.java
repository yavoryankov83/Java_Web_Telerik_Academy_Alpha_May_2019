package com.telerikacademy.users;

import com.telerikacademy.insurance.HealthInsurancePlan;

public class Patient extends User {
    //private boolean isInsured;
    private HealthInsurancePlan insurancePlan;

    public Patient(long id, String firstName, String lastName, String gender, String email) {
        super(id, firstName, lastName, gender, email);
        //this.isInsured = isInsured;
    }

    public boolean isInsured() {
        return insurancePlan != null;
    }

//    public void setInsured(boolean insured) {
//        this.isInsured = insured;
//    }

    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }
}
