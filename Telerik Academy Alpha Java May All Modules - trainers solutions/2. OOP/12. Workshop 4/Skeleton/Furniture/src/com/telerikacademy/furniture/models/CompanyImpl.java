package com.telerikacademy.furniture.models;

import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;

import java.util.List;

public class CompanyImpl implements Company {
    // Finish the class

    private String name;
    private String registrationNumber;
    private List<Furniture> furnitures;

    public CompanyImpl(String name, String registrationNumber) {
        //Initialize the state
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public List<Furniture> getFurnitures() {
        return furnitures;
    }

    public void add(Furniture furniture) {
    }

    public String catalog() {
        StringBuilder strBuilder = new StringBuilder();
        // Finish it

        return strBuilder.toString().trim();
    }

    public Furniture find(String model) {
        return null;
    }

    public void remove(Furniture furniture) {
    }
}
