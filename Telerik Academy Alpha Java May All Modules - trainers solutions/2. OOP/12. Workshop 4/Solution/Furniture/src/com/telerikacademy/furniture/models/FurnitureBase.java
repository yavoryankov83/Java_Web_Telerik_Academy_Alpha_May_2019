package com.telerikacademy.furniture.models;

import com.telerikacademy.furniture.models.contracts.Furniture;
import com.telerikacademy.furniture.models.enums.MaterialType;

public class FurnitureBase implements Furniture {
    private static final int MODEL_MIN_LENGTH = 3;

    private String model;
    private MaterialType materialType;
    private double price;
    private double height;

    protected FurnitureBase(String model, MaterialType materialType, double price, double height) {
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
        return String.format("Type: %s, Model: %s, Material: %s, Price: %.2f, Height: %.2f%s",
                getFurnitureType(),
                model,
                materialType,
                price,
                height,
                additionalInfo());
    }

    private String getFurnitureType() {
        return this.getClass().getSimpleName().replace("Impl", "");
    }

    protected String additionalInfo() {
        return "";
    }

    private void setModel(String model) {
        ValidationHelper.checkValueMinLength(model, MODEL_MIN_LENGTH);
        this.model = model;
    }

    private void setPrice(double price) {
        ValidationHelper.checkValueNotNegative(price);
        this.price = price;
    }

    protected void setHeight(double height) {
        ValidationHelper.checkValueNotNegative(height);
        this.height = height;
    }

    @Override
    public int compareTo(Furniture f) {
        int result = Double.compare(price, f.getPrice());
        if (result == 0) {
            return model.compareTo(f.getModel());
        }
        return result;
    }
}
