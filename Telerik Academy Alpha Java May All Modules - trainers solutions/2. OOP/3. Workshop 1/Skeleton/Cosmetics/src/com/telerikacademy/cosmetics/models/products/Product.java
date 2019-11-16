package com.telerikacademy.cosmetics.models.products;

import com.telerikacademy.cosmetics.models.common.GenderType;

public class Product {
    private double price;
    private String name;
    private String brand;
    private GenderType gender;

    public Product(String name, String brand, double price, GenderType gender) {
        setPrice(price);
    }

    public String print() {
        return "";
        // Format:
        //" #[Name] [Brand]
        // #Price: [Price]
        // #Gender: [Gender]
        // ==="
    }
}
