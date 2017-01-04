package fr.unice.polytech.al.trafficlight.route.provider;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * A simple hello world service
 * <p>
 * Created by tom dall'agnol on 25/12/16.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/hello")
public class HelloWorldService {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public String sayHello() {
        return "hello";
    }
}
