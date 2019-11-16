package com.telerikacademy.agency.models;

public class ValidationHelper {
    public static <T extends Number> void validateValueInRange(T value, T min, T max, String errorMessage) {
        if (value.doubleValue() < min.doubleValue()
                || value.doubleValue() > max.doubleValue()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateNotNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }
}
