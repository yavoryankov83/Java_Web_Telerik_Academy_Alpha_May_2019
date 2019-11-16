package com.telerikacademy.io;

public class Resource2 implements AutoCloseable {
    public Resource2() {
        System.out.println("Hello from Resource 2");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing Resource 2");
    }
}
