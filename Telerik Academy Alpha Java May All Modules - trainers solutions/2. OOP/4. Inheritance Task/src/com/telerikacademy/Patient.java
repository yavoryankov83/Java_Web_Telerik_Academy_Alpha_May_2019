package com.telerikacademy;

public class Patient extends User {
    private boolean isInsured;

    public Patient(long id, String firstName, String lastName, String gender, String email, boolean isInsured) {
        super(id, firstName, lastName, gender, email);
        this.isInsured = isInsured;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        this.isInsured = insured;
    }
}
