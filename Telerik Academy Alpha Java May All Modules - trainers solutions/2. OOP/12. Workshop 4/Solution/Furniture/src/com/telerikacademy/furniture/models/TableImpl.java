package com.telerikacademy.furniture.models;

import com.telerikacademy.furniture.models.contracts.Table;
import com.telerikacademy.furniture.models.enums.MaterialType;

public class TableImpl extends FurnitureBase implements Table {
    private double length, width;

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
        return length * width;
    }

    @Override
    protected String additionalInfo() {
        return String.format(", Length: %.2f, Width: %.2f, Area: %.4f",
                length,
                width,
                getArea());
    }

    private void setLength(double length) {
        ValidationHelper.checkValueNotNegative(length);
        this.length = length;
    }

    private void setWidth(double width) {
        ValidationHelper.checkValueNotNegative(width);
        this.width = width;
    }
}
