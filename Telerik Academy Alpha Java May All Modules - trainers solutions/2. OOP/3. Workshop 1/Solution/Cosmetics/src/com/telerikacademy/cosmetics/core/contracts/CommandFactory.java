package com.telerikacademy.cosmetics.core.contracts;

import com.telerikacademy.cosmetics.commands.contracts.Command;

public interface CommandFactory {
    Command createCommand(String commandTypeAsString, CosmeticsFactory cosmeticsFactory, CosmeticsRepository cosmeticsRepository);
}
