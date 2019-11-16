package com.telerikacademy.cosmetics.models.cart;

import com.telerikacademy.cosmetics.models.products.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> productList;

    public ShoppingCart() {
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public void addProduct(Product product) {
        throw new NotImplementedException();
    }

    public void removeProduct(Product product) {
        throw new NotImplementedException();
    }

    public boolean containsProduct(Product product) {
        throw new NotImplementedException();
    }

    public double totalPrice() {
        throw new NotImplementedException();
    }
}
