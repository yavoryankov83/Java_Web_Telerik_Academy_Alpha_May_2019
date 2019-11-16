package com.telerikacademy.exceptions.trywithresources;

public class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("Closed MyResource");
    }
}
