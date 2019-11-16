package com.telerikacademy.furniture.models;

import com.telerikacademy.furniture.models.contracts.Chair;
import com.telerikacademy.furniture.models.enums.MaterialType;

public class ChairImpl extends FurnitureBase implements Chair {
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
        return String.format(", Legs: %d", numberOfLegs);
    }

    private void setNumberOfLegs(int numberOfLegs) {
        ValidationHelper.checkValueNotNegative(numberOfLegs);
        this.numberOfLegs = numberOfLegs;
    }
}
