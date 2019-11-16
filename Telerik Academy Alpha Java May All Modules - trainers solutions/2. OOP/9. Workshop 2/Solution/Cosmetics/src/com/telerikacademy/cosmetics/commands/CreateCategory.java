package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.core.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.contracts.Category;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.CATEGORY_CREATED;
import static com.telerikacademy.cosmetics.commands.CommandConstants.CATEGORY_EXISTS;

public class CreateCategory implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public CreateCategory(CosmeticsRepository cosmeticsRepository, CosmeticsFactory cosmeticsFactory) {
        this.cosmeticsRepository = cosmeticsRepository;
        this.cosmeticsFactory = cosmeticsFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        String categoryName = parameters.get(0);
        return createCategory(categoryName);
    }

    private String createCategory(String categoryName) {
        if (cosmeticsRepository.getCategories().containsKey(categoryName)) {
            return String.format(CATEGORY_EXISTS, categoryName);
        }

        Category category = cosmeticsFactory.createCategory(categoryName);
        cosmeticsRepository.getCategories().put(categoryName, category);

        return String.format(CATEGORY_CREATED, categoryName);
    }
}
