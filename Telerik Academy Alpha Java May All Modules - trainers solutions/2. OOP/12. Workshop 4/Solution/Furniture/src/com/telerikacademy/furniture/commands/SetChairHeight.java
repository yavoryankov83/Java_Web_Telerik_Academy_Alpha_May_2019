package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.AdjustableChair;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class SetChairHeight implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    public SetChairHeight(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String adjChairModel = parameters.get(0);
        double adjChairHeight = Double.parseDouble(parameters.get(1));
        return adjustChairHeight(adjChairModel, adjChairHeight);
    }

    private String adjustChairHeight(String model, double height) {
        if (!furnitureRepository.getFurnitures().containsKey(model)) {
            return String.format(CommandConstants.FURNITURE_NOT_FOUND_ERROR_MESSAGE, model);
        }

        AdjustableChair adjChair = (AdjustableChair) furnitureRepository.getFurnitures().get(model);
        if (adjChair == null) {
            return String.format(CommandConstants.FURNITURE_IS_NOT_ADJUSTABLE_CHAIR_ERROR_MESSAGE, model);
        }

        adjChair.setHeight(height);

        return String.format(CommandConstants.CHAIR_HEIGHT_ADJUSTED_SUCCESS_MESSAGE, model, height);
    }
}
