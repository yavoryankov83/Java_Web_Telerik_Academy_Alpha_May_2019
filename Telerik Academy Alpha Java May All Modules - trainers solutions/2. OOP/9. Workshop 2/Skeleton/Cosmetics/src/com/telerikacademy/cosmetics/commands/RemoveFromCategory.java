package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.core.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.contracts.Category;
import com.telerikacademy.cosmetics.models.contracts.Product;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.*;

public class RemoveFromCategory implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public RemoveFromCategory(CosmeticsRepository cosmeticsRepository, CosmeticsFactory cosmeticsFactory) {
        this.cosmeticsRepository = cosmeticsRepository;
        this.cosmeticsFactory = cosmeticsFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        String categoryNameToRemove = parameters.get(0);
        String productToRemove = parameters.get(1);
        return removeCategory(categoryNameToRemove, productToRemove);
    }

    private String removeCategory(String categoryNameToRemove, String productToRemove) {
        if (!cosmeticsRepository.getCategories().containsKey(categoryNameToRemove)) {
            return String.format(CATEGORY_DOES_NOT_EXIST, categoryNameToRemove);
        }

        if (!cosmeticsRepository.getProducts().containsKey(productToRemove)) {
            return String.format(PRODUCT_DOES_NOT_EXIST, productToRemove);
        }

        Category category = cosmeticsRepository.getCategories().get(categoryNameToRemove);
        Product product = cosmeticsRepository.getProducts().get(productToRemove);

        category.removeProduct(product);

        return String.format(PRODUCT_REMOVED_CATEGORY, productToRemove, categoryNameToRemove);
    }
}
