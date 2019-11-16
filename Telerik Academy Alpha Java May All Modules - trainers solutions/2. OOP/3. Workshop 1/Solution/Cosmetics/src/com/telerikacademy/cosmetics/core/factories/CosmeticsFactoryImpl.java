package com.telerikacademy.cosmetics.core.factories;

import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.models.Category;
import com.telerikacademy.cosmetics.models.cart.ShoppingCart;
import com.telerikacademy.cosmetics.models.common.GenderType;
import com.telerikacademy.cosmetics.models.products.Product;

public class CosmeticsFactoryImpl implements CosmeticsFactory {
    public Category createCategory(String name) {
        return new Category(name);
    }

    public Product createProduct(String name, String brand, double price, String gender) {
//        try {
//            GenderType genderType = GenderType.valueOf(gender.toUpperCase());
//            return new Product(name, brand, price, genderType);
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException(
//                    String.format("Gender must be one of the values %s",
//                            GenderType.values()));
//        }

        return new Product(name, brand, price, GenderType.valueOf(gender.toUpperCase()));
    }

    public ShoppingCart createShoppingCart() {
        return new ShoppingCart();
    }
}
