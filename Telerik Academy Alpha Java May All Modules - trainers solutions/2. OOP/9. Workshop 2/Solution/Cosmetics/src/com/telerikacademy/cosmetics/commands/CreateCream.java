package com.telerikacademy.cosmetics.commands;

import com.telerikacademy.cosmetics.core.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class CreateCream implements Command {
    private CosmeticsRepository cosmeticsRepository;
    private CosmeticsFactory cosmeticsFactory;

    public CreateCream(CosmeticsRepository cosmeticsRepository, CosmeticsFactory cosmeticsFactory) {
        this.cosmeticsRepository = cosmeticsRepository;
        this.cosmeticsFactory = cosmeticsFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        throw new NotImplementedException();
    }
}
