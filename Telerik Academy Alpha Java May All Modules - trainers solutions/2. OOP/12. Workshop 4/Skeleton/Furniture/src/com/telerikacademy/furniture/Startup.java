package com.telerikacademy.furniture;

import com.telerikacademy.furniture.core.EngineImpl;

public class Startup {

    public static void main(String[] args) {
        EngineImpl engine = new EngineImpl();
        engine.start();
    }

}
