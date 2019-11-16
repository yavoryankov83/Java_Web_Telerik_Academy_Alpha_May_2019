package com.telerikacademy.cosmetics.models.contracts;

import java.util.List;

public interface ShoppingCart {
    List<Product> getProductList();
    void addProduct(Product product);
    void removeProduct(Product product);
    boolean containsProduct(Product product);
    double totalPrice();
}
