package com.telerikacademy.furniture.core.contracts;

import com.telerikacademy.furniture.commands.contracts.Command;

public interface CommandFactory {
    Command createCommand(String commandTypeAsString, FurnitureFactory factory, FurnitureRepository agencyRepository);
}
