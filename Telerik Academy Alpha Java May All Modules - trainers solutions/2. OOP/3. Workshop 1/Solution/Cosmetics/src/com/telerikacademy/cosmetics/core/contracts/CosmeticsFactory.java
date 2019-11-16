package com.telerikacademy.cosmetics.core.contracts;

import com.telerikacademy.cosmetics.models.Category;
import com.telerikacademy.cosmetics.models.cart.ShoppingCart;
import com.telerikacademy.cosmetics.models.products.Product;

public interface CosmeticsFactory {
    Category createCategory(String name);
    Product createProduct(String name, String brand, double price, String gender);
    ShoppingCart createShoppingCart();
}