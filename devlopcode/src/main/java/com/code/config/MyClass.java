package com.code.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyClass.class);

    public void doSomething() {
        LOGGER.info("This is an info message.");
    }
}