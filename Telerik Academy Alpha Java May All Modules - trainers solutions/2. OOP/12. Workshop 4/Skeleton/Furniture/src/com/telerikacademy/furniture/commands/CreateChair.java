package com.telerikacademy.furniture.commands;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.models.contracts.Chair;

import java.util.List;

import static com.telerikacademy.furniture.commands.CommandConstants.*;

public class CreateChair implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 6;

    private final FurnitureRepository furnitureRepository;
    private final FurnitureFactory furnitureFactory;

    public CreateChair(FurnitureRepository furnitureRepository, FurnitureFactory furnitureFactory) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureFactory = furnitureFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }

        String chairModel = parameters.get(0);
        String chairMaterial = parameters.get(1);
        double chairPrice = Double.parseDouble(parameters.get(2));
        double chairHeight = Double.parseDouble(parameters.get(3));
        int chairLegs = Integer.parseInt(parameters.get(4));
        String chairType = parameters.get(5);
        return createChair(chairModel, chairMaterial, chairPrice, chairHeight, chairLegs, chairType);
    }

    private String createChair(String model, String material, double price, double height, int legs, String type) {
        if (furnitureRepository.getFurnitures().containsKey(model)) {
            return String.format(FURNITURE_EXISTS_ERROR_MESSAGE, model);
        }

        Chair chair = furnitureFactory.createChair(type, model, material, price, height, legs);
        furnitureRepository.addFurniture(model, chair);

        return String.format(CHAIR_CREATED_SUCCESS_MESSAGE, model);
    }
}
