package com.dai.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DataWebServiceApplication {


    //TODO Remove the test endpoint and @RestController annotation
    @GetMapping("/")
    public String getMessage() {
        return "Rest API test";
    }

    public static void main(String[] args) {
        SpringApplication.run(DataWebServiceApplication.class, args);
    }

}
