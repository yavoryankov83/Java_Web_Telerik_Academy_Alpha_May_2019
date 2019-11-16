package com.telerikacademy.cosmetics.core;

import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.Category;
import com.telerikacademy.cosmetics.models.cart.ShoppingCart;
import com.telerikacademy.cosmetics.models.products.Product;

import java.util.HashMap;
import java.util.Map;

public class CosmeticsRepositoryImpl implements CosmeticsRepository {
    private final ShoppingCart shoppingCart;
    private final Map<String, Category> categories;
    private final Map<String, Product> products;

    public CosmeticsRepositoryImpl() {
        shoppingCart = new ShoppingCart();
        categories = new HashMap<>();
        products = new HashMap<>();
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public Map<String, Category> getCategories() {
        return categories;
    }

    @Override
    public Map<String, Product> getProducts() {
        return products;
    }
}
