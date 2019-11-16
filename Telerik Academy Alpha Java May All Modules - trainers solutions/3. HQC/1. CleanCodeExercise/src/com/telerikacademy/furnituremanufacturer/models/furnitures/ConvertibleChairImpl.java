package com.telerikacademy.furnituremanufacturer.models.furnitures;

import com.telerikacademy.furnituremanufacturer.interfaces.ConvertibleChair;
import com.telerikacademy.furnituremanufacturer.models.MaterialType;

public class ConvertibleChairImpl extends ChairImpl implements ConvertibleChair {
    private static final String CONVERTED = "Converted";
    private static final String NORMAL_POSITION = "Normal";
    private static final double CONVERTED_HEIGHT = 0.10;

    private boolean isConverted;

    public ConvertibleChairImpl(String model, MaterialType materialType, double price, double height, int numberOfLegs, boolean isConverted) {
        super(model, materialType, price, height, numberOfLegs);
        this.isConverted = isConverted;
    }

    @Override
    public boolean getIsConverted() {
        return isConverted;
    }

    @Override
    public void convert() {
        isConverted = !isConverted;
        if (isConverted) {
            setHeight(CONVERTED_HEIGHT);
        }
    }

    @Override
    protected String additionalInfo() {
        return String.format(", State: %s", getIsConverted() ? CONVERTED : NORMAL_POSITION);
    }
}
