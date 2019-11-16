package com.telerikacademy.springiocdemo.codeconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyApplication {

    private Logger myLogger;

    @Autowired
    public MyApplication(Logger myLogger) {
        this.myLogger = myLogger;
    }

    public void run() {
        myLogger.logMessage("Spring FTW!!!!");
    }

}
