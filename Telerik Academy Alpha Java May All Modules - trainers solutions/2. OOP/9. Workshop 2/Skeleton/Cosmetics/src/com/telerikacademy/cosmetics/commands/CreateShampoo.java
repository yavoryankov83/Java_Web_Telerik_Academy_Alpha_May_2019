package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.core.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.models.common.GenderType;
import com.telerikacademy.cosmetics.models.common.UsageType;
import com.telerikacademy.cosmetics.models.contracts.Shampoo;

import java.util.List;

import static com.telerikacademy.cosmetics.commands.CommandConstants.SHAMPOO_ALREADY_EXIST;
import static com.telerikacademy.cosmetics.commands.CommandConstants.SHAMPOO_CREATED;

public class CreateShampoo implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public CreateShampoo(CosmeticsRepository cosmeticsRepository, CosmeticsFactory cosmeticsFactory) {
        this.cosmeticsRepository = cosmeticsRepository;
        this.cosmeticsFactory = cosmeticsFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        String shampooName = parameters.get(0);
        String shampooBrand = parameters.get(1);
        double shampooPrice = Double.parseDouble(parameters.get(2));
        GenderType shampooGender = GenderType.valueOf(parameters.get(3).toUpperCase());
        int shampooMilliliters = Integer.parseInt(parameters.get(4));
        UsageType shampooUsage = UsageType.valueOf(parameters.get(5).toUpperCase());
        return createShampoo(shampooName, shampooBrand, shampooPrice, shampooGender, shampooMilliliters, shampooUsage);
    }

    private String createShampoo(String shampooName, String shampooBrand, double shampooPrice, GenderType shampooGender, int shampooMilliliters, UsageType shampooUsage) {
        if (cosmeticsRepository.getProducts().containsKey(shampooName)) {
            return String.format(SHAMPOO_ALREADY_EXIST, shampooName);
        }

        Shampoo shampoo = cosmeticsFactory.createShampoo(shampooName, shampooBrand, shampooPrice, shampooGender, shampooMilliliters, shampooUsage);
        cosmeticsRepository.getProducts().put(shampooName, shampoo);

        return String.format(SHAMPOO_CREATED, shampooName);
    }
}
