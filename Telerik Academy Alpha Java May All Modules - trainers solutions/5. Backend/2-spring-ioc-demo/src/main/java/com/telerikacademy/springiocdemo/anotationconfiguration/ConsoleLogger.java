package com.telerikacademy.springiocdemo.anotationconfiguration;

import org.springframework.stereotype.Component;

@Component("MyConsoleLogger")
public class ConsoleLogger implements Logger {
    @Override
    public void logMessage(String message) {
        System.out.println(message);
    }
}
