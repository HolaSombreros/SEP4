package com.dai.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DataWebServiceApplication {

    @GetMapping("/")
    public String getMessage() {
        return "Rest API test for docker";
    }

    public static void main(String[] args) {
        SpringApplication.run(DataWebServiceApplication.class, args);
    }
}
