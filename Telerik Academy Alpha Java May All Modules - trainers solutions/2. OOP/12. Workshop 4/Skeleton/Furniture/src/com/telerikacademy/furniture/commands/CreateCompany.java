package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.Company;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.*;

public class CreateCompany implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    public CreateCompany(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    @Override
    public String execute(List<String> parameters) {

        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String companyName = parameters.get(0);
        String companyRegistrationNumber = parameters.get(1);
        return createCompany(companyName, companyRegistrationNumber);
    }

    private String createCompany(String name, String registrationNumber) {

        if (furnitureRepository.getCompanies().containsKey(name)) {
            return String.format(COMPANY_EXISTS_ERROR_MESSAGE, name);
        }

        Company company = furnitureFactory.createCompany(name, registrationNumber);
        furnitureRepository.addCompany(name, company);

        return String.format(COMPANY_CREATED_SUCCESS_MESSAGE, name);
    }
}
