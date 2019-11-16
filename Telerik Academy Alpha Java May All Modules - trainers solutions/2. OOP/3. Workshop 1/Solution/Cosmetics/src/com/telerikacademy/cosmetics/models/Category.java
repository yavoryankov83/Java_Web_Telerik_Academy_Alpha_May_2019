package com.telerikacademy.cosmetics.models;

import com.telerikacademy.cosmetics.models.common.ValidationHelper;
import com.telerikacademy.cosmetics.models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_INVALID_MESSAGE = "Category name should be between 2 and 15 symbols.";
    private static final String REMOVE_ERROR_FORMAT = "Product %s doesn't exist in category %s";

    private String name;
    private List<Product> products;

    public Category(String name) {
        setName(name);
        products = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_INVALID_MESSAGE);
        }
        this.name = name;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        ValidationHelper.checkProductNotNull(product);
        products.add(product);
    }

    public void removeProduct(Product product) {
        ValidationHelper.checkProductNotNull(product);
        if (!products.contains(product)) {
            throw new IllegalArgumentException(
                    String.format(
                            REMOVE_ERROR_FORMAT,
                            product.getName(),
                            name));
        }
        products.remove(product);
    }

    public String print() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("#Category: %s\n", name));
        if (products.isEmpty()) {
            result.append(" #No product in this category");
        }

        for (Product product : products) {
            result.append(product.print());
        }

        return result.toString();
    }
}
