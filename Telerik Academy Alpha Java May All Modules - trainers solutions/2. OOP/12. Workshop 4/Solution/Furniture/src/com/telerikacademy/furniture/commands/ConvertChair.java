package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.ConvertibleChair;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class ConvertChair implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    public ConvertChair(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String convertibleChairModel = parameters.get(0);
        return convertChair(convertibleChairModel);
    }

    private String convertChair(String model) {
        if (!furnitureRepository.getFurnitures().containsKey(model)) {
            return String.format(CommandConstants.FURNITURE_NOT_FOUND_ERROR_MESSAGE, model);
        }

        ConvertibleChair convChair = (ConvertibleChair) furnitureRepository.getFurnitures().get(model);
        if (convChair == null) {
            return String.format(CommandConstants.FURNITURE_IS_NOT_CONVERTIBLE_CHAIR_ERROR_MESSAGE, model);
        }

        convChair.convert();

        return String.format(CommandConstants.CHAIR_STATE_CONVERTED_SUCCESS_MESSAGE, model);
    }
}
