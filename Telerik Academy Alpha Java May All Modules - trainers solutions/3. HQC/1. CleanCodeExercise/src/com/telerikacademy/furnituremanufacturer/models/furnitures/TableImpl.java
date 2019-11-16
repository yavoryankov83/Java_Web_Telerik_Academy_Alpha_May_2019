package com.telerikacademy.furnituremanufacturer.models.furnitures;


import com.telerikacademy.furnituremanufacturer.interfaces.Table;
import com.telerikacademy.furnituremanufacturer.models.MaterialType;
import com.telerikacademy.furnituremanufacturer.models.ValidationHelper;

public class TableImpl extends FurnitureBase implements Table {
    private static final String LENGTH_ERROR_MESSAGE = "Length should be greater than 0!";
    private static final String WIDTH_ERROR_MESSAGE = "Width should be greater than 0!";

    private double length;
    private double width;

    public TableImpl(String model, MaterialType materialType, double price, double height, double length, double width) {
        super(model, materialType, price, height);
        setLength(length);
        setWidth(width);
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return getLength() * getWidth();
    }

    @Override
    protected String additionalInfo() {
        return String.format(
                ", Length: %.2f, Width: %.2f, Area: %.4f",
                getLength(),
                getWidth(),
                getArea());
    }

    private void setLength(double length) {
        ValidationHelper.checkValueNotNegative(length, LENGTH_ERROR_MESSAGE);
        this.length = length;
    }

    private void setWidth(double width) {
        ValidationHelper.checkValueNotNegative(width, WIDTH_ERROR_MESSAGE);
        this.width = width;
    }
}
