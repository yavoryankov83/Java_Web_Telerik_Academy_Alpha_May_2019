package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.commands.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.Category;
import com.telerikacademy.cosmetics.models.products.Product;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.*;

public class AddToCategory implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public AddToCategory(CosmeticsFactory cosmeticsFactory, CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsFactory = cosmeticsFactory;
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        String categoryNameToAdd = parameters.get(0);
        String productToAdd = parameters.get(1);
        return addToCategory(categoryNameToAdd, productToAdd);
    }

    private String addToCategory(String categoryNameToAdd, String productToAdd) {
        if (!cosmeticsRepository.getCategories().containsKey(categoryNameToAdd)) {
            return String.format(CATEGORY_DOES_NOT_EXIST, categoryNameToAdd);
        }

        if (!cosmeticsRepository.getProducts().containsKey(productToAdd)) {
            return String.format(PRODUCT_DOES_NOT_EXIST, productToAdd);
        }

        Category category = cosmeticsRepository.getCategories().get(categoryNameToAdd);
        Product product = cosmeticsRepository.getProducts().get(productToAdd);

        category.addProduct(product);

        return String.format(PRODUCT_ADDED_TO_CATEGORY, productToAdd, categoryNameToAdd);
    }
}
