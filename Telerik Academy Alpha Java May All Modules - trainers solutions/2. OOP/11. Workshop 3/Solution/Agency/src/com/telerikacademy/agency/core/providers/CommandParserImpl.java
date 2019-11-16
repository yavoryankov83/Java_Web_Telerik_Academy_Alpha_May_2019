package com.telerikacademy.agency.core.providers;

import com.telerikacademy.agency.core.contracts.CommandParser;

import java.util.*;

public class CommandParserImpl implements CommandParser {

    public String parseCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public List<String> parseParameters(String fullCommand) {
        String[] commandParts = fullCommand.split(" ");
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }
}
