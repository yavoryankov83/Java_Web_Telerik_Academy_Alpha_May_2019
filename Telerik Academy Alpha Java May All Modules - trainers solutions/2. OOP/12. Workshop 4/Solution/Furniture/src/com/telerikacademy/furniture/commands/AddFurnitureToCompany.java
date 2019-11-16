package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.*;

public class AddFurnitureToCompany implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    public AddFurnitureToCompany(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }

        String companyToAddToName = parameters.get(0);
        String furnitureToBeAddedName = parameters.get(1);
        if (!furnitureRepository.getCompanies().containsKey(companyToAddToName)) {
            throw new IllegalArgumentException(String.format(COMPANY_NOT_FOUND_ERROR_MESSAGE, companyToAddToName));
        }

        if (!furnitureRepository.getFurnitures().containsKey(furnitureToBeAddedName)) {
            throw new IllegalArgumentException(String.format(FURNITURE_NOT_FOUND_ERROR_MESSAGE, furnitureToBeAddedName));
        }

        Company company = furnitureRepository.getCompanies().get(companyToAddToName);
        Furniture furniture = furnitureRepository.getFurnitures().get(furnitureToBeAddedName);
        company.add(furniture);

        return String.format(FURNITURE_ADDED_SUCCESS_MESSAGE, furnitureToBeAddedName, companyToAddToName);
    }
}
