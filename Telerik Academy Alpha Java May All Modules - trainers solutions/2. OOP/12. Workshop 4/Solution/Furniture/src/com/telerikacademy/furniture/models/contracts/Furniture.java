package com.telerikacademy.furniture.models.contracts;

import com.telerikacademy.furniture.models.FurnitureBase;
import com.telerikacademy.furniture.models.enums.MaterialType;

public interface Furniture extends Comparable<Furniture> {
    String getModel();

    MaterialType getMaterialType();

    double getPrice();

    double getHeight();
}
