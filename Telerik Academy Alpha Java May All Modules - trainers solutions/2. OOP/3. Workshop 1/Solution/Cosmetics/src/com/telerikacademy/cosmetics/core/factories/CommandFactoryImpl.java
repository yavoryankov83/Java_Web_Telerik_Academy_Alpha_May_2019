package com.telerikacademy.cosmetics.core.factories;

import com.telerikacademy.cosmetics.commands.*;
import com.telerikacademy.cosmetics.commands.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.CommandFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsFactory;
import com.telerikacademy.cosmetics.core.contracts.CosmeticsRepository;
import com.telerikacademy.cosmetics.commands.enums.CommandType;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommand(String commandTypeAsString, CosmeticsFactory cosmeticsFactory, CosmeticsRepository cosmeticsRepository) {
        CommandType commandType = CommandType.valueOf(commandTypeAsString.toUpperCase());
        switch (commandType) {
            case CREATECATEGORY:
                return new CreateCategory(cosmeticsFactory,cosmeticsRepository);
            case TOTALPRICE:
                return new TotalPrice(cosmeticsFactory,cosmeticsRepository);
            case SHOWCATEGORY:
                return new ShowCategory(cosmeticsFactory,cosmeticsRepository);
            case ADDTOCATEGORY:
                return new AddToCategory(cosmeticsFactory, cosmeticsRepository);
            case CREATEPRODUCT:
                return new CreateProduct(cosmeticsFactory,cosmeticsRepository);
            case ADDTOSHOPPINGCART:
                return new AddToShoppingCart(cosmeticsFactory,cosmeticsRepository);
            case REMOVEFROMSHOPPINGCART:
                return new RemoveFromShoppingCart(cosmeticsFactory,cosmeticsRepository);
            case REMOVEFROMCATEGORY:
                return  new RemoveFromCategory(cosmeticsFactory, cosmeticsRepository);
                default:
                    throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }

    }
}
