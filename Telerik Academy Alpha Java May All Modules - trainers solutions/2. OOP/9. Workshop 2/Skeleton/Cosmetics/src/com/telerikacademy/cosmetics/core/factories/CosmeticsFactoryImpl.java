package com.telerikacademy.cosmetics.core.factories;

import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.models.cart.ShoppingCartImpl;
import com.telerikacademy.cosmetics.models.common.GenderType;
import com.telerikacademy.cosmetics.models.common.UsageType;
import com.telerikacademy.cosmetics.models.contracts.*;
import com.telerikacademy.cosmetics.models.products.ShampooImpl;
import com.telerikacademy.cosmetics.models.products.ToothpasteImpl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class CosmeticsFactoryImpl implements CosmeticsFactory {
    @Override
    public Category createCategory(String name) {
        throw new NotImplementedException();
    }

    @Override
    public ShampooImpl createShampoo(String name, String brand, double price, GenderType gender, int milliliters, UsageType usage) {
        throw new NotImplementedException();
    }

    @Override
    public ToothpasteImpl createToothpaste(String name, String brand, double price, GenderType gender, List<String> ingredients) {
        throw new NotImplementedException();
    }

    @Override
    public ShoppingCartImpl createShoppingCart() {
        throw new NotImplementedException();
    }

// advance task

//    @Override
//    public Cream createCream(String name, String brand, double price, GenderType gender, ScentType scent) {
//        throw new NotImplementedException();
//    }
}
