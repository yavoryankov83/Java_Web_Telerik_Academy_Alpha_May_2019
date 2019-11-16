package com.telerikacademy.furniture.models;

public class ValidationHelper {
    public static void checkValueNotNegative(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkValueMinLength(String value, int minLength) {
        if (value == null || value.length() < minLength) {
            throw new IllegalArgumentException();
        }
    }
}
