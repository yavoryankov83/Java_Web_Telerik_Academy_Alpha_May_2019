package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.core.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.contracts.Product;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.*;

public class RemoveFromShoppingCart implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public RemoveFromShoppingCart(CosmeticsRepository cosmeticsRepository, CosmeticsFactory cosmeticsFactory) {
        this.cosmeticsRepository = cosmeticsRepository;
        this.cosmeticsFactory = cosmeticsFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        String productToRemoveFromCart = parameters.get(0);
        return removeFromShoppingCart(productToRemoveFromCart);
    }

    private String removeFromShoppingCart(String productName) {
        if (!cosmeticsRepository.getProducts().containsKey(productName)) {
            return String.format(PRODUCT_DOES_NOT_EXIST, productName);
        }

        Product product = cosmeticsRepository.getProducts().get(productName);

        if (!cosmeticsRepository.getShoppingCart().containsProduct(product)) {
            return String.format(PRODUCT_DOES_NOT_EXIST_IN_SHOPPING_CART, productName);
        }

        cosmeticsRepository.getShoppingCart().removeProduct(product);

        return String.format(PRODUCT_REMOVED_FROM_SHOPPING_CART, productName);
    }
}
