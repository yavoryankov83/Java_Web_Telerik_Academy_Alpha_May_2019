package com.telerikacademy.io;

public class Resource1 implements AutoCloseable {

    public Resource1() {
        System.out.println("Hello from Resource 1");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing Resource 1");
    }
}
