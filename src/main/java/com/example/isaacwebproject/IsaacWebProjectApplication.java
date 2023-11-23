package com.example.isaacwebproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class IsaacWebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsaacWebProjectApplication.class, args);
    }

}
