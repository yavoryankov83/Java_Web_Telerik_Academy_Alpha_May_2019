package com.telerikacademy.cosmetics.tests.cosmeticsfactory;

import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.factories.CosmeticsFactoryImpl;
import com.telerikacademy.cosmetics.models.contracts.ShoppingCart;
import org.junit.Assert;
import org.junit.Test;

public class CreateShoppingCart_Should {
    @Test
    public void returnInstanceOfTypeShoppingCart() {
        // Arrange, Act, Assert
        CosmeticsFactory factory = new CosmeticsFactoryImpl();

        // Act
        ShoppingCart cart = factory.createShoppingCart();

        // Assert
        Assert.assertTrue(cart instanceof ShoppingCart);
    }
}
