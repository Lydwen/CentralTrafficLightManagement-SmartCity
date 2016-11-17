package fr.unice.polytech.al.trafficlight.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@ImportResource("WEB-INF/applicationContext.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}