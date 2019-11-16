package com.telerikacademy.furnituremanufacturer.interfaces.engine;

import com.telerikacademy.furnituremanufacturer.interfaces.Company;
import com.telerikacademy.furnituremanufacturer.interfaces.Furniture;

import java.util.List;

public interface CompanyFactory {

    Company createCompany(String name, String registrationNumber);
}
