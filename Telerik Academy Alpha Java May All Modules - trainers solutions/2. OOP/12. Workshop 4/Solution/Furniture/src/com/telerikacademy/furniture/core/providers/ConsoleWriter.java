package com.telerikacademy.furniture.core.providers;

import com.telerikacademy.furniture.core.contracts.Writer;

public class ConsoleWriter implements Writer {
    public void write(String message) {
        System.out.print(message);
    }

    public void writeLine(String message) {
        System.out.println(message);
    }
}
