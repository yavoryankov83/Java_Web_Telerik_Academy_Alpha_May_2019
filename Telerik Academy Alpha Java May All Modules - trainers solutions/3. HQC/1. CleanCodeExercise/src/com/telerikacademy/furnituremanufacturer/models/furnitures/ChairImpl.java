package com.telerikacademy.furnituremanufacturer.models.furnitures;

import com.telerikacademy.furnituremanufacturer.interfaces.Chair;
import com.telerikacademy.furnituremanufacturer.models.MaterialType;
import static com.telerikacademy.furnituremanufacturer.models.ValidationHelper.*;

public class ChairImpl extends FurnitureBase implements Chair {
    private static final String LEGS_ERROR_MESSAGE = "A chair should have at least one leg";

    private int numberOfLegs;

    public ChairImpl(String model, MaterialType materialType, double price, double height, int numberOfLegs) {
        super(model, materialType, price, height);
        setNumberOfLegs(numberOfLegs);
    }

    @Override
    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    @Override
    protected String additionalInfo() {
        return String.format(", Legs: %d", getNumberOfLegs());
    }

    private void setNumberOfLegs(int numberOfLegs) {
        checkValueNotNegative(numberOfLegs, LEGS_ERROR_MESSAGE);
        this.numberOfLegs = numberOfLegs;
    }
}
