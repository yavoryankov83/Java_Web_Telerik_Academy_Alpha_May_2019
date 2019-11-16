package com.telerikacademy.cosmetics.models;

import com.telerikacademy.cosmetics.models.products.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Category {
    private String name;
    private List<Product> products;

    public Category(String name) {
    }

    public List<Product> getProducts() {
        throw new NotImplementedException();
    }

    public void addProduct(Product product) {
        throw new NotImplementedException();
    }

    public void removeProduct(Product product) {
        throw new NotImplementedException();
    }

    public String print() {
        throw new NotImplementedException();
    }
}
