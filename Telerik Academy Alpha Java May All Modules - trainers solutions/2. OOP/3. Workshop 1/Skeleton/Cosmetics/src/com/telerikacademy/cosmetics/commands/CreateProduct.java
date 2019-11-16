package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.commands.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.products.Product;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.PRODUCT_ALREADY_EXIST;
import static com.telerikacademy.cosmetics.commands.CommandConstants.PRODUCT_CREATED;

public class CreateProduct implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public CreateProduct(CosmeticsFactory cosmeticsFactory, CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsFactory = cosmeticsFactory;
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        String shampooName = parameters.get(0);
        String shampooBrand = parameters.get(1);
        double shampooPrice = Double.parseDouble(parameters.get(2));
        String shampooGender = parameters.get(3);
        return createProduct(shampooName, shampooBrand, shampooPrice, shampooGender);
    }

    private String createProduct(String name, String brand, double price, String gender) {
        if (cosmeticsRepository.getProducts().containsKey(name)) {
            return String.format(PRODUCT_ALREADY_EXIST, name);
        }

        Product shampoo = cosmeticsFactory.createProduct(name, brand, price, gender);
        cosmeticsRepository.getProducts().put(name, shampoo);

        return String.format(PRODUCT_CREATED, name);
    }
}
