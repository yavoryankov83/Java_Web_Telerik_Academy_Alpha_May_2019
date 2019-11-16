package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.*;

public class RemoveFurnitureFromCompany implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    public RemoveFurnitureFromCompany(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    @Override
    public String execute(List<String> parameters) {

        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String companyToRemoveFrom = parameters.get(0);
        String furnitureToBeRemoved = parameters.get(1);
        return removeFurnitureFromCompany(companyToRemoveFrom, furnitureToBeRemoved);
    }

    private String removeFurnitureFromCompany(String companyName, String furnitureName) {
        if (!furnitureRepository.getCompanies().containsKey(companyName)) {
            return String.format(COMPANY_NOT_FOUND_ERROR_MESSAGE, companyName);
        }

        if (!furnitureRepository.getFurnitures().containsKey(furnitureName)) {
            return String.format(FURNITURE_NOT_FOUND_ERROR_MESSAGE, furnitureName);
        }

        Company company = furnitureRepository.getCompanies().get(companyName);
        Furniture furniture = furnitureRepository.getFurnitures().get(furnitureName);
        company.remove(furniture);

        return String.format(FURNITURE_REMOVED_SUCCESS_MESSAGE, furnitureName, companyName);
    }
}
