package com.telerikacademy.springiocdemo.codeconfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        MyApplication app = context.getBean(MyApplication.class);
        app.run();
    }

    @Bean
    public Logger createLoggerInstance() {
        return new ConsoleLogger();
    }

    @Bean
    public MyApplication appInstance() {
        return new MyApplication(createLoggerInstance());
    }


}
