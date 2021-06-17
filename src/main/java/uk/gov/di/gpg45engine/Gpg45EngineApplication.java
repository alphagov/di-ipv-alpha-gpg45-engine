package uk.gov.di.gpg45engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class Gpg45EngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(Gpg45EngineApplication.class, args);
    }

}
