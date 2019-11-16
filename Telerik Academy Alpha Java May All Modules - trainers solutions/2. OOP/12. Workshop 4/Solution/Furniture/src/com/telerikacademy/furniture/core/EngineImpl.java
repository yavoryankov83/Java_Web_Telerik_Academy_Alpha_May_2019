package com.telerikacademy.furniture.core;

import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.contracts.*;
import com.telerikacademy.furniture.core.factories.CommandFactoryImpl;
import com.telerikacademy.furniture.core.factories.FurnitureFactoryImpl;
import com.telerikacademy.furniture.core.providers.CommandParserImpl;
import com.telerikacademy.furniture.core.providers.ConsoleReader;
import com.telerikacademy.furniture.core.providers.ConsoleWriter;

import java.util.List;

public class EngineImpl implements Engine {
    private static final String TERMINATION_COMMAND = "Exit";

    private FurnitureFactory furnitureFactory;
    private CommandParser commandParser;
    private FurnitureRepository furnitureRepository;
    private Writer writer;
    private Reader reader;
    private CommandFactory commandFactory;

    public EngineImpl() {
        furnitureFactory = new FurnitureFactoryImpl();
        commandParser = new CommandParserImpl();
        writer = new ConsoleWriter();
        reader = new ConsoleReader();
        commandFactory = new CommandFactoryImpl();
        furnitureRepository = new FurnitureRepositoryImpl();
    }

    @Override
    public void start() {
        while (true) {
            try {
                String commandAsString = reader.readLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);

            } catch (Exception ex) {
                writer.writeLine(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : ex.toString());
            }
        }
    }

    private void processCommand(String commandAsString) {
        if (commandAsString == null || commandAsString.trim().equals("")) {
            throw new IllegalArgumentException("Command cannot be null or empty.");
        }

        String commandName = commandParser.parseCommand(commandAsString);
        Command command = commandFactory.createCommand(commandName, furnitureFactory, furnitureRepository);
        List<String> parameters = commandParser.parseParameters(commandAsString);
        String executionResult = command.execute(parameters);
        writer.writeLine(executionResult);
    }
}
