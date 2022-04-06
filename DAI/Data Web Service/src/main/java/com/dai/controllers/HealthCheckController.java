package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.shared.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

//Controller used for AWS to check whenever the service works or not
@RequestMapping("/healthcheck")
@RestController
public class HealthCheckController {

    @GetMapping(value = "/")
    public String read() {
        return "Data Service Health Check";
    }
}
