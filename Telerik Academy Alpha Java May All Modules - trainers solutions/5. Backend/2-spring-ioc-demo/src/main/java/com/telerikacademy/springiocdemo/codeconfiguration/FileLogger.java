package com.telerikacademy.springiocdemo.codeconfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

public class FileLogger implements Logger {
    @Override
    public void logMessage(String message) {
        Date now = new Date();
        String time = new Timestamp(now.getTime()).toString();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("log.txt"), StandardCharsets.UTF_8))) {
            writer.write(String.format("DEBUG MODEEEE!: [%s] %s \n", time, message));
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }
}
