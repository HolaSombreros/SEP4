package com.dai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class DataWebServiceApplication {

    @GetMapping("/")
    public String getMessage() {
        return "Health Check";
    }

    public static void main(String[] args) {
        SpringApplication.run(DataWebServiceApplication.class, args);
    }

}
