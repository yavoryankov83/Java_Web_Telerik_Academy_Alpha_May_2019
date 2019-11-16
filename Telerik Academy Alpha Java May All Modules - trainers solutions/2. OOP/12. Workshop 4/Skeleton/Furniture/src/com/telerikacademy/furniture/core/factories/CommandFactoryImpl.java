package com.telerikacademy.furniture.core.factories;

import com.telerikacademy.furniture.commands.*;
import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.commands.enums.CommandType;
import com.telerikacademy.furniture.core.contracts.CommandFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommand(String commandTypeAsString, FurnitureFactory furnitureFactory, FurnitureRepository furnitureRepository) {
        CommandType commandType = CommandType.valueOf(commandTypeAsString.toUpperCase());
        switch (commandType) {
            case CREATECHAIR:
                return new CreateChair(furnitureRepository, furnitureFactory);
            case CREATETABLE:
                return new CreateTable(furnitureRepository, furnitureFactory);
            case CONVERTCHAIR:
                return new ConvertChair(furnitureRepository, furnitureFactory);
            case CREATECOMPANY:
                return new CreateCompany(furnitureRepository, furnitureFactory);
            case SETCHAIRHEIGHT:
                return new SetChairHeight(furnitureRepository, furnitureFactory);
            case SHOWCOMPANYCATALOG:
                return new ShowCompanyCatalog(furnitureRepository, furnitureFactory);
            case ADDFURNITURETOCOMPANY:
                return new AddFurnitureToCompany(furnitureRepository, furnitureFactory);
            case FINDFURNITUREFROMCOMPANY:
                return new FindFurnitureFromCompany(furnitureRepository, furnitureFactory);
            case REMOVEFURNITUREFROMCOMPANY:
                return new RemoveFurnitureFromCompany(furnitureRepository, furnitureFactory);
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}
