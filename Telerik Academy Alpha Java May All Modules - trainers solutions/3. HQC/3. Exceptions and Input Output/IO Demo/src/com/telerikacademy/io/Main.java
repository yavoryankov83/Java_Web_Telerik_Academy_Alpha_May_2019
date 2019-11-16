package com.telerikacademy.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("test.txt");

        // Write your name and age to the file
        try (PrintWriter pw = new PrintWriter(file);
             Scanner scanner = new Scanner(file)) {
            pw.println("Bla");
            pw.println(10);
           // scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // test how resources are closed
        try {
            workWithResources();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void workWithResources() throws Exception {
        try (Resource1 r1 = new Resource1();
             Resource2 r2 = new Resource2();
             Resource3 r3 = new Resource3()) {
            System.out.println("Working with these resources!!!!");
        }
    }
}
