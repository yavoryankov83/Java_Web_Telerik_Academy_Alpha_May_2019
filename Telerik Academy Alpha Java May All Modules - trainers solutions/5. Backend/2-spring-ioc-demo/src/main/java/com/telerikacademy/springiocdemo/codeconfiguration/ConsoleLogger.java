package com.telerikacademy.springiocdemo.codeconfiguration;

public class ConsoleLogger implements Logger {
    @Override
    public void logMessage(String message) {
        System.out.println("CODE CONFIG");
    }
}
