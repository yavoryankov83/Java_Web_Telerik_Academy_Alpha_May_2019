package com.telerikacademy.agency.core;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.core.contracts.*;
import com.telerikacademy.agency.core.contracts.AgencyFactory;
import com.telerikacademy.agency.core.contracts.CommandParser;
import com.telerikacademy.agency.core.factories.AgencyFactoryImpl;
import com.telerikacademy.agency.core.contracts.CommandFactory;
import com.telerikacademy.agency.core.factories.CommandFactoryImpl;
import com.telerikacademy.agency.core.providers.*;

import java.util.List;

public class EngineImpl implements Engine {
    private static final String TERMINATION_COMMAND = "Exit";

    private Reader reader;
    private Writer writer;
    private AgencyFactory agencyFactory;
    private CommandFactory commandFactory;
    private CommandParser commandParser;
    private AgencyRepository agencyRepository;


    public EngineImpl() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.agencyFactory = new AgencyFactoryImpl();
        this.commandFactory = new CommandFactoryImpl();
        this.commandParser = new CommandParserImpl();
        this.agencyRepository = new AgencyRepositoryImpl();
    }

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
        Command command = commandFactory.createCommand(commandName, agencyFactory, agencyRepository);
        List<String> parameters = commandParser.parseParameters(commandAsString);
        String executionResult = command.execute(parameters);
        writer.writeLine(executionResult);
    }
}
