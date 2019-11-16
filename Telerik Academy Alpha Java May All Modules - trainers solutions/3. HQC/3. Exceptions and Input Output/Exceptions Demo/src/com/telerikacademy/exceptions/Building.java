package com.telerikacademy.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Building {
    void color() throws FileNotFoundException {
        System.out.println("Blue");
        throw new IllegalArgumentException("");
    }
}

