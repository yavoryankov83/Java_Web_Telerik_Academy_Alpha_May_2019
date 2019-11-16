package com.telerikacademy.cosmetics.core.factories;

import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.models.Category;
import com.telerikacademy.cosmetics.models.cart.ShoppingCart;
import com.telerikacademy.cosmetics.models.products.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CosmeticsFactoryImpl implements CosmeticsFactory {
    public Category createCategory(String name) {
        return new Category(name);
    }

    public Product createProduct(String name, String brand, double price, String gender) {
        throw new NotImplementedException();
    }

    public ShoppingCart createShoppingCart() {
        return new ShoppingCart();
    }
}
