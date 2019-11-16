package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.*;

public class FindFurnitureFromCompany implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    public FindFurnitureFromCompany(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    @Override
    public String execute(List<String> parameters) {

        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String companyToFindFrom = parameters.get(0);
        String furnitureToBeFound = parameters.get(1);
        return findFurnitureFromCompany(companyToFindFrom, furnitureToBeFound);
    }

    private String findFurnitureFromCompany(String companyName, String furnitureName) {
        if (!furnitureRepository.getCompanies().containsKey(companyName)) {
            return String.format(COMPANY_NOT_FOUND_ERROR_MESSAGE, companyName);
        }

        Company company = furnitureRepository.getCompanies().get(companyName);
        Furniture furniture = company.find(furnitureName);
        if (furniture == null) {
            return String.format(FURNITURE_NOT_FOUND_ERROR_MESSAGE, furnitureName);
        }

        return furniture.toString();
    }
}
