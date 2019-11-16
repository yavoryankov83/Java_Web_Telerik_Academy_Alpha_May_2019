package com.telerikacademy.cosmetics.models.common;

import com.telerikacademy.cosmetics.models.products.Product;

public class ValidationHelper {
    private ValidationHelper() {

    }

    public static void checkProductNotNull(Product product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
    }
}
