package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.COMPANY_NOT_FOUND_ERROR_MESSAGE;
import static com.telerikacademy.furniture.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class ShowCompanyCatalog implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

    public ShowCompanyCatalog(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String companyToShowCatalog = parameters.get(0);
        return showCompanyCatalog(companyToShowCatalog);
    }

    private String showCompanyCatalog(String companyName) {
        if (!furnitureRepository.getCompanies().containsKey(companyName)) {
            return String.format(COMPANY_NOT_FOUND_ERROR_MESSAGE, companyName);
        }

        return furnitureRepository.getCompanies().get(companyName).catalog();
    }
}
