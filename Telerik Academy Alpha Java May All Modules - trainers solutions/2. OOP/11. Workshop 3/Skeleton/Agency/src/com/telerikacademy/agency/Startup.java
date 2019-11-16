package com.telerikacademy.agency;

import com.telerikacademy.agency.core.EngineImpl;

public class Startup {
    public static void main(String[] args) {

        EngineImpl engine = new EngineImpl();
        engine.start();
    }
}
