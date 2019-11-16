package com.telerikacademy.cosmetics.core.contracts;

import com.telerikacademy.cosmetics.models.contracts.Category;
import com.telerikacademy.cosmetics.models.contracts.Product;
import com.telerikacademy.cosmetics.models.contracts.ShoppingCart;

import java.util.Map;

public interface CosmeticsRepository {
    public ShoppingCart getShoppingCart();

    public Map<String, Category> getCategories();

    public Map<String, Product> getProducts();
}
