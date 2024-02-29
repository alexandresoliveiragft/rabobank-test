package dev.alexandreoliveira.gft.rabobank.travels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class RabobankTravelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabobankTravelsApplication.class, args);
    }
}
