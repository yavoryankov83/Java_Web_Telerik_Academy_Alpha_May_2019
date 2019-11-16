package com.telerikacademy.springiocdemo.anotationconfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.telerikacademy.springiocdemo.demo.anotationconfig")
public class Main {
    public static void main(String[] args) {
//        Logger logger = new ConsoleLogger();
//        Application app = new Application(logger);
//        app.run();

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        MyApplication app = context.getBean(MyApplication.class);
        app.run();

    }
}
