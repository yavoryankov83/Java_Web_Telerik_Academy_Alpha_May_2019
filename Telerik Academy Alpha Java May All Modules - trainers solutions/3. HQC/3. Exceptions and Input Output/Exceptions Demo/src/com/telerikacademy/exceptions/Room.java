package com.telerikacademy.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLDataException;

public class Room extends Building {
    void color() throws FileNotFoundException {
        System.out.println("White");
        throw  new FileNotFoundException();
    }
}
