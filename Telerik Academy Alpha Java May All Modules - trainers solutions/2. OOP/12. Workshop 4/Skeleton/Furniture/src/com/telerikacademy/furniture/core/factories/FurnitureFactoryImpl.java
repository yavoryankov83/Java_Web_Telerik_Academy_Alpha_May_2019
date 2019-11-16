package com.telerikacademy.furniture.core.factories;

import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.models.contracts.Chair;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Table;
import com.telerikacademy.furniture.models.enums.MaterialType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FurnitureFactoryImpl implements FurnitureFactory {
    @Override
    public Company createCompany(String name, String registrationNumber) {
        throw new NotImplementedException();
    }

    @Override
    public Table createTable(String model, String materialType, double price, double height, double length, double width) {
        throw new NotImplementedException();
    }

    @Override
    public Chair createChair(String type, String model, String material, double price, double height, int numberOfLegs) {
        throw new NotImplementedException();
    }

    private MaterialType getMaterialType(String material) {
        return MaterialType.valueOf(material.toUpperCase());
    }
}
