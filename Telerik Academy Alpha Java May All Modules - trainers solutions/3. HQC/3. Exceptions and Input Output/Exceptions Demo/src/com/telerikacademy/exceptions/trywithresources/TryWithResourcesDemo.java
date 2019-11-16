// Master Java Exceptions with Best Practices
package com.telerikacademy.exceptions.trywithresources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TryWithResourcesDemo {

    public static void main(String[] args) throws Exception {
        // Create file
        // File file = new File("myFirstFile.txt");
        // write some data in it
        // writeFile(file);
        // read the data from it
        // readFile(file);

        // Use Scanner to read from File. Make sure the scanner closes the handle to the file
        // trivialMethod();

        // Use it with try-with-resources - no need to care about the Scanner anymore
        // shortenMethod();

        // Use multiple resources with try-with-resources
        multipleResources();
    }

    public static void writeFile(File file) {

        // Write your name and age to the file
        try {
            PrintWriter output = new PrintWriter(file);
            output.println("Nadya");
            output.println("33");
            output.close();
        } catch (FileNotFoundException e) {
            System.out.printf("ERROR: %s\n", e);
        }
    }

    public static void readFile(File file) {
        try {
            Scanner input = new Scanner(file);
            String name = input.nextLine();
            int age = input.nextInt();
            System.out.printf("Name: %s\n Age: %d\n", name, age);
        } catch (FileNotFoundException e) {
            System.out.printf("ERROR: %s\n", e);
        }
    }

    public static void trivialMethod() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("test.txt"));
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
    }

    public static void shortenMethod() {
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public static void multipleResources() {
        try (Scanner scanner = new Scanner(new File("testRead.txt"));
             PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
            while (scanner.hasNext()) {
                writer.println(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public static void customAutoClosable() {
        try (Scanner scanner = new Scanner(new File("testRead.txt"));
             PrintWriter writer = new PrintWriter(new File("testWrite.txt"));
             MyResource myResource = new MyResource();) {
            while (scanner.hasNext()) {
                writer.print(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void orderOfClosingResources() throws Exception {
        try (AutoCloseableResourcesFirst af = new AutoCloseableResourcesFirst();
             AutoCloseableResourcesSecond as = new AutoCloseableResourcesSecond()) {

            af.doSomething();
            as.doSomething();
        }
    }

}

