package com.telerikacademy.furniture.core;

import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;

import java.util.HashMap;
import java.util.Map;

public class FurnitureRepositoryImpl implements FurnitureRepository {
    private Map<String, Company> companies;
    private Map<String, Furniture> furnitures;

    public FurnitureRepositoryImpl() {
        this.companies = new HashMap<>();
        this.furnitures = new HashMap<>();
    }

    public Map<String, Company> getCompanies() {
        return new HashMap<>(companies);
    }

    public Map<String, Furniture> getFurnitures() {
        return new HashMap<>(furnitures);
    }

    public void addCompany(String name, Company company) {
        this.companies.put(name, company);
    }

    public void addFurniture(String model, Furniture furniture) {
        this.furnitures.put(model, furniture);
    }
}
