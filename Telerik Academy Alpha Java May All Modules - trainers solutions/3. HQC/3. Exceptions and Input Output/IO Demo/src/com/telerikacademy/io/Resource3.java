package com.telerikacademy.io;

public class Resource3 implements AutoCloseable {

    public Resource3() {
        System.out.println("Hello from Resource 3");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing Resource 3");
    }
}
