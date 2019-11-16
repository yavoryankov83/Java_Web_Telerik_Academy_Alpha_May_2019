package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.commands.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.products.Product;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.PRODUCT_ADDED_TO_SHOPPING_CART;
import static com.telerikacademy.cosmetics.commands.CommandConstants.PRODUCT_DOES_NOT_EXIST;

public class AddToShoppingCart implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public AddToShoppingCart(CosmeticsFactory cosmeticsFactory, CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsFactory = cosmeticsFactory;
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        String productToAddToCart = parameters.get(0);
        return addToShoppingCart(productToAddToCart);
    }

    private String addToShoppingCart(String productName) {
        if (!cosmeticsRepository.getProducts().containsKey(productName)) {
            return String.format(PRODUCT_DOES_NOT_EXIST, productName);
        }

        Product product = cosmeticsRepository.getProducts().get(productName);
        cosmeticsRepository.getShoppingCart().addProduct(product);

        return String.format(PRODUCT_ADDED_TO_SHOPPING_CART, productName);
    }
}
