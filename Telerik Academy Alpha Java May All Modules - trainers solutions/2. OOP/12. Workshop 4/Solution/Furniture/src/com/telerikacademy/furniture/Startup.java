package com.telerikacademy.furniture;

import com.telerikacademy.furniture.core.EngineImpl;

import java.util.Locale;

public class Startup {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        EngineImpl engine = new EngineImpl();
        engine.start();
    }
}
