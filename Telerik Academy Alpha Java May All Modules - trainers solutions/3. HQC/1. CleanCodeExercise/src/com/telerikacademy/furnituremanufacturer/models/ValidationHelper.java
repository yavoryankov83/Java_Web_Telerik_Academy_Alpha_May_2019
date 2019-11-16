package com.telerikacademy.furnituremanufacturer.models;

public class ValidationHelper {
    public static void checkValuePositive(double value, String errorMessage) {
        if (value <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkValueNotNegative(double value, String errorMessage) {
        if (value < 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
