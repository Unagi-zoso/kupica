package org.nightdivers.kupica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class KupicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KupicaApplication.class, args);
    }

}
