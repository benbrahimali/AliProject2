package com.university.restaurant1.gatway_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayeApiApplication.class, args);
    }

}
