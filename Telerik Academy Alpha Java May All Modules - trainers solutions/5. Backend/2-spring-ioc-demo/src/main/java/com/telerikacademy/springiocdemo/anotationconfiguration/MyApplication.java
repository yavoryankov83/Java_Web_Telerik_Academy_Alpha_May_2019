package com.telerikacademy.springiocdemo.anotationconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyApplication {

    private Logger myLogger;

    @Autowired
    public MyApplication(@Qualifier("MyFileLogger") Logger myLogger) {
        this.myLogger = myLogger;
    }

    public void run() {
        myLogger.logMessage("Spring FTW!!!!");
    }
}
