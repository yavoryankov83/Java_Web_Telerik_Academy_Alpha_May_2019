package com.telerikacademy.furniture.core.contracts;

import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;

import java.util.Map;

public interface FurnitureRepository {
    Map<String, Company> getCompanies();

    Map<String, Furniture> getFurnitures();

    void addCompany(String name, Company company);

    void addFurniture(String name, Furniture furniture);
}
