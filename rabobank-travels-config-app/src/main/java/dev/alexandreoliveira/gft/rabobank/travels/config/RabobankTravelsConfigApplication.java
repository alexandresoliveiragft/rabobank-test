package dev.alexandreoliveira.gft.rabobank.travels.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RabobankTravelsConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabobankTravelsConfigApplication.class, args);
    }
}
