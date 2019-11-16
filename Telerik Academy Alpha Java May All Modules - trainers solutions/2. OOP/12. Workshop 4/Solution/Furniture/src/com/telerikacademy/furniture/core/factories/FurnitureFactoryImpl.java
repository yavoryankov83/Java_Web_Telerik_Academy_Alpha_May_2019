package com.telerikacademy.furniture.core.factories;

import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.models.*;
import com.telerikacademy.furniture.models.contracts.Chair;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Table;
import com.telerikacademy.furniture.models.enums.MaterialType;

public class FurnitureFactoryImpl implements FurnitureFactory {
    @Override
    public Company createCompany(String name, String registrationNumber) {
        return new CompanyImpl(name, registrationNumber);
    }

    @Override
    public Table createTable(String model, String materialType, double price, double height, double length, double width) {
        MaterialType materialType1Value = getMaterialType(materialType);
        return new TableImpl(model, materialType1Value, price, height, length, width);
    }

    @Override
    public Chair createChair(String type, String model, String material, double price, double height, int numberOfLegs) {
        MaterialType materialType = getMaterialType(material);
        switch (type.toLowerCase()) {
            case "normal":
                return new ChairImpl(model, materialType, price, height, numberOfLegs);
            case "adjustable":
                return new AdjustableChairImpl(model, materialType, price, height, numberOfLegs);
            case "convertible":
                return new ConvertibleChairImpl(model, materialType, price, height, numberOfLegs);
            default:
                throw new IllegalArgumentException();
        }
    }

    private MaterialType getMaterialType(String material) {
        return MaterialType.valueOf(material.toUpperCase());
    }
}
