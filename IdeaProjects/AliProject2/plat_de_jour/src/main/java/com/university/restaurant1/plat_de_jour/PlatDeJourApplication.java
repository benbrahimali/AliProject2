package com.university.restaurant1.plat_de_jour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class PlatDeJourApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatDeJourApplication.class, args);
    }

}
