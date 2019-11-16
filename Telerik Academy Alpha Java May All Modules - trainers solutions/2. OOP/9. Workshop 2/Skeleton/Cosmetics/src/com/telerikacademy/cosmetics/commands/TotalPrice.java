package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.core.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.contracts.ShoppingCart;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.TOTAL_PRICE_IN_SHOPPING_CART;

public class TotalPrice implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public TotalPrice(CosmeticsRepository cosmeticsRepository, CosmeticsFactory cosmeticsFactory) {
        this.cosmeticsRepository = cosmeticsRepository;
        this.cosmeticsFactory = cosmeticsFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        ShoppingCart shoppingCart = cosmeticsRepository.getShoppingCart();
        if (shoppingCart.getProductList().size() == 0) {
            return "No product in shopping cart!";
        }
        return String.format(TOTAL_PRICE_IN_SHOPPING_CART, shoppingCart.totalPrice());
    }
}
