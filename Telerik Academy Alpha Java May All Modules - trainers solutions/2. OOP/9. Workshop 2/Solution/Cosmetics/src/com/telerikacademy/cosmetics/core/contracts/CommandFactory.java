package com.telerikacademy.cosmetics.core.contracts;

public interface CommandFactory {
    Command createCommand(String commandTypeAsString, CosmeticsFactory factory, CosmeticsRepository cosmeticsRepository);
}
