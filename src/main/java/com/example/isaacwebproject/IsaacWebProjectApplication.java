package com.example.isaacwebproject;

import com.example.isaacwebproject.config.SessionConfig;
import com.example.isaacwebproject.gameServer.miniprojectServer.ServerControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class IsaacWebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsaacWebProjectApplication.class, args);
    }
}
