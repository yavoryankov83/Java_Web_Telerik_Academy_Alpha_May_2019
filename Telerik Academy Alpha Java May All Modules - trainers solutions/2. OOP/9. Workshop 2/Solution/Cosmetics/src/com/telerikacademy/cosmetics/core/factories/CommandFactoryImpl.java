package com.telerikacademy.cosmetics.core.factories;

import com.telerikacademy.cosmetics.commands.*;
import com.telerikacademy.cosmetics.commands.enums.CommandType;
import com.telerikacademy.cosmetics.core.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CommandFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommand(String commandTypeAsString, CosmeticsFactory cosmeticsFactory, CosmeticsRepository cosmeticsRepository) {
        CommandType commandType = CommandType.valueOf(commandTypeAsString.toUpperCase());

        switch (commandType) {
            case REMOVEFROMSHOPPINGCART:
                return new RemoveFromShoppingCart(cosmeticsRepository, cosmeticsFactory);
            case ADDTOSHOPPINGCART:
                return new AddToShoppingCart(cosmeticsRepository, cosmeticsFactory);
            case ADDTOCATEGORY:
                return new AddToCategory(cosmeticsRepository, cosmeticsFactory);
            case SHOWCATEGORY:
                return new ShowCategory(cosmeticsRepository, cosmeticsFactory);
            case CREATECREAM:
                return new CreateCream(cosmeticsRepository, cosmeticsFactory);
            case TOTALPRICE:
                return new TotalPrice(cosmeticsRepository, cosmeticsFactory);
            case CREATESHAMPOO:
                return new CreateShampoo(cosmeticsRepository, cosmeticsFactory);
            case CREATETOOTHPASTE:
                return new CreateToothpaste(cosmeticsRepository, cosmeticsFactory);
            case CREATECATEGORY:
                return new CreateCategory(cosmeticsRepository, cosmeticsFactory);
            case REMOVEFROMCATEGORY:
                return new RemoveFromCategory(cosmeticsRepository, cosmeticsFactory);
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}
