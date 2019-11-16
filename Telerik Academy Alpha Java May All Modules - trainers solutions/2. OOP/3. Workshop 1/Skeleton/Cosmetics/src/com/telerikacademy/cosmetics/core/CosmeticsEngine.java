package com.telerikacademy.cosmetics.core;

import com.telerikacademy.cosmetics.commands.contracts.Command;
import com.telerikacademy.cosmetics.core.contracts.*;
import com.telerikacademy.cosmetics.core.factories.CommandFactoryImpl;
import com.telerikacademy.cosmetics.core.factories.CosmeticsFactoryImpl;
import com.telerikacademy.cosmetics.core.providers.CommandParser;

import java.util.*;

public final class CosmeticsEngine implements Engine {
    public static final String TERMINATION_COMMAND = "Exit";

    private final CosmeticsFactory cosmeticsFactory;
    private final CosmeticsRepository cosmeticsRepository;
    private final Parser commandParser;
    private final CommandFactory commandFactory;

    public CosmeticsEngine() {
        cosmeticsFactory = new CosmeticsFactoryImpl();
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        commandParser = new CommandParser();
        commandFactory = new CommandFactoryImpl();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String commandAsString = sc.nextLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);
            } catch (Exception ex) {
                System.out.println((ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : ex.toString()));
            }
        }
    }

    private void processCommand(String commandAsString) {
        if (commandAsString == null || commandAsString.trim().equals("")) {
            throw new IllegalArgumentException("Command cannot be null or empty.");
        }

        String commandName = commandParser.parseCommand(commandAsString);
        Command command = commandFactory.createCommand(commandName, cosmeticsFactory, cosmeticsRepository);
        List<String> parameters = commandParser.parseParameters(commandAsString);
        String executionResult = command.execute(parameters);
        System.out.println(executionResult);
    }
}
