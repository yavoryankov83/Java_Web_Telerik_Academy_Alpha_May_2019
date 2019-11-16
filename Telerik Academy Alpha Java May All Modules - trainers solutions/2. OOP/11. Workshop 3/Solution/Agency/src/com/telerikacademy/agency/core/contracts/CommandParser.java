package com.telerikacademy.agency.core.contracts;

import java.util.List;

public interface CommandParser {
    String parseCommand(String fullCommand);

    List<String> parseParameters(String fullCommand);
}
