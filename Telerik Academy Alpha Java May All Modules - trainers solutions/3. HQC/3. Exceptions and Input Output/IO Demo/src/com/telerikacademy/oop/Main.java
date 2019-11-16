package com.telerikacademy.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("test.txt");

        // Write your name and age to the file
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            pw.println("nadya");
            pw.println("33");
            pw.println("Yey!");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        //read from file
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }


        //you can comment out the writing and change the file's content to see that reading is working

    }
}
