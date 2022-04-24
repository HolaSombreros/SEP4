package com.dai.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

//Controller used for AWS to check whenever the service works or not
@RequestMapping("/healthcheck")
@RestController
public class HealthCheckController {

    @GetMapping
    public String read() {
        return "Balabalala";
    }
}
