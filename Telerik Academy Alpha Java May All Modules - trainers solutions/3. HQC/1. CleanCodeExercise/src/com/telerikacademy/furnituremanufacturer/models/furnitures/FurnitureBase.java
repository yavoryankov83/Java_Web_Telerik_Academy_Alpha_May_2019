package com.telerikacademy.furnituremanufacturer.models.furnitures;

import com.telerikacademy.furnituremanufacturer.interfaces.Furniture;
import com.telerikacademy.furnituremanufacturer.models.MaterialType;
import com.telerikacademy.furnituremanufacturer.models.ValidationHelper;

public abstract class FurnitureBase implements Furniture {
    private static final int MODEL_MIN_LENGTH = 3;
    private static final String HEIGHT_ERROR_MESSAGE = "Height should not be less than zero";
    private static final String PRICE_ERROR_MESSAGE = "Price should not be less than zero";
    private static final String MODEL_ERROR_MESSAGE = "Model should not be less than 3 symbols";

    private String model;
    private MaterialType materialType;
    private double price;
    private double height;

    public FurnitureBase(String model, MaterialType materialType, double price, double height) {
        setModel(model);
        setPrice(price);
        setHeight(height);
        this.materialType = materialType;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public MaterialType getMaterialType() {
        return materialType;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format(
                "Type: %s, Model: %s, Material: %s, Price: %.2f, Height: %.2f %s\n",
                getFurnitureType(),
                getModel(),
                getMaterialType(),
                getPrice(),
                getHeight(),
                additionalInfo());
    }

    protected abstract String additionalInfo();

    protected void setHeight(double height) {
        ValidationHelper.checkValuePositive(height, HEIGHT_ERROR_MESSAGE);
        this.height = height;
    }

    private void setPrice(double price) {
        ValidationHelper.checkValuePositive(price, PRICE_ERROR_MESSAGE);
        this.price = price;
    }

    private void setModel(String model) {
        if (model.length() < MODEL_MIN_LENGTH) {
            throw new IllegalArgumentException(MODEL_ERROR_MESSAGE);
        }
        this.model = model;
    }

    private String getFurnitureType() {
        return getClass().getSimpleName().replace("Impl", "");
    }
}