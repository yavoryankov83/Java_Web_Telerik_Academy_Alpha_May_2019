package com.telerikacademy.cosmetics.tests.cosmeticsfactory;

import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.factories.CosmeticsFactoryImpl;
import com.telerikacademy.cosmetics.models.common.GenderType;
import com.telerikacademy.cosmetics.models.contracts.Product;
import com.telerikacademy.cosmetics.models.contracts.Toothpaste;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CreateToothpaste_Should {
    @Test
    public void returnInstanceOfTypeProduct() {
        // Arrange
        CosmeticsFactory factory = new CosmeticsFactoryImpl();
        // Act
        Toothpaste toothpaste = factory.createToothpaste("name", "brand", 10, GenderType.UNISEX, new ArrayList<>());
        // Assert
        Assert.assertTrue(toothpaste instanceof Product);
    }
}
