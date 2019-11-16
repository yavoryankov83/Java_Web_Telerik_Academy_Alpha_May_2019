package com.telerikacademy.cosmetics.core.contracts;

import java.util.List;

public interface CommandParser {
    String parseCommand(String fullCommand);

    List<String> parseParameters(String fullCommand);
}
