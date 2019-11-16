package com.telerikacademy.furniture.core.contracts;

import com.telerikacademy.furniture.models.contracts.Chair;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Table;

public interface FurnitureFactory {
    Company createCompany(String name, String registrationNumber);

    Table createTable(String model, String materialType, double price, double height, double length, double width);

    Chair createChair(String type, String model, String material, double price, double height, int numberOfLegs);
}
