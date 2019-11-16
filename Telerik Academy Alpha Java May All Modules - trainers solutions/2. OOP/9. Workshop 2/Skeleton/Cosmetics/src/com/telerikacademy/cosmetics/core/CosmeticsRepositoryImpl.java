package com.telerikacademy.cosmetics.core;

import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.cart.ShoppingCartImpl;
import com.telerikacademy.cosmetics.models.contracts.Category;
import com.telerikacademy.cosmetics.models.contracts.Product;
import com.telerikacademy.cosmetics.models.contracts.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class CosmeticsRepositoryImpl implements CosmeticsRepository {
    private final ShoppingCart shoppingCart;
    private final Map<String, Category> categories;
    private final Map<String, Product> products;

    public CosmeticsRepositoryImpl() {
        shoppingCart = new ShoppingCartImpl();
        categories = new HashMap<>();
        products = new HashMap<>();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}
