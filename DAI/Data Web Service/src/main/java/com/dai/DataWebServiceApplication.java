package com.dai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@EnableAsync
@SpringBootApplication
public class DataWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataWebServiceApplication.class, args);
    }

}
