package com.telerikacademy.furniture.models.contracts;

import com.telerikacademy.furniture.models.enums.MaterialType;

public interface Furniture {
    String getModel();

    MaterialType getMaterialType();

    double getPrice();

    double getHeight();
}
