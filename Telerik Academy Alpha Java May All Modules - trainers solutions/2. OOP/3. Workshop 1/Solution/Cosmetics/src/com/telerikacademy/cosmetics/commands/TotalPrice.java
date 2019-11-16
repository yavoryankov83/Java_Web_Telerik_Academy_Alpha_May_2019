package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.commands.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.cart.ShoppingCart;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.TOTAL_PRICE_IN_SHOPPING_CART;

public class TotalPrice implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public TotalPrice(CosmeticsFactory cosmeticsFactory, CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsFactory = cosmeticsFactory;
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ShoppingCart cart = cosmeticsRepository.getShoppingCart();
        if (cart.getProductList().size() == 0) {
            return "No product in shopping cart!";
        }
        return String.format(TOTAL_PRICE_IN_SHOPPING_CART, cart.totalPrice());
    }
}
