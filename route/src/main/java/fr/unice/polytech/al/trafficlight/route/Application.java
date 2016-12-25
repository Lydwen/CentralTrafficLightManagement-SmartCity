package fr.unice.polytech.al.trafficlight.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by user on 25/12/16.
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@ImportResource("WEB-INF/applicationContext.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
