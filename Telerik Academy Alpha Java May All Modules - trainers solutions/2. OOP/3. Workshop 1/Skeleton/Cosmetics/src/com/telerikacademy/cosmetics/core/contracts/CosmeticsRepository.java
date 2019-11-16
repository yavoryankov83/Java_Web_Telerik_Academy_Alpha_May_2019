package com.telerikacademy.cosmetics.core.contracts;

import com.telerikacademy.cosmetics.models.Category;
import com.telerikacademy.cosmetics.models.cart.ShoppingCart;
import com.telerikacademy.cosmetics.models.products.Product;

import java.util.Map;

public interface CosmeticsRepository {
    ShoppingCart getShoppingCart();
    Map<String, Category> getCategories();
    Map<String, Product> getProducts();
}
