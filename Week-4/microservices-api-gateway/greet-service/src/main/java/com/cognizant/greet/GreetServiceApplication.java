package com.cognizant.greet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetController {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(GreetController.class);

    @GetMapping
    public String greet() {
        LOGGER.info("Greet service called!");
        return "Hello from Greet Service!";
    }

    @GetMapping("/hello")
    public String hello() {
        LOGGER.info("Hello endpoint called!");
        return "Hello World from Greet Microservice!";
    }
}