package com.example.isaacwebproject.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2Config {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server H2DBServer() throws SQLException{
        return Server.createTcpServer("-tcp","-tcpAllowOthers","-tcpPort","9091");
    }
}
