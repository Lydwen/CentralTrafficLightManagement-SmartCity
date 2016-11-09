package fr.unice.polytech.al.trafficlight.central.provider;


import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.central.data.TrafficLight;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;

import java.util.ArrayList;
import java.util.List;

/**
 * Crossroad service.
 *
 * @author Tom Dall'Agnol
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value="/crossroad")
public class CrossroadService {

    /**
     * Crossroad web requester.
     */
    //private WebRequester crossroadRequester = new WebRequester("crossroads", "/crossroad");

    /**
     * Retrieves all the existing crossroads
     * in the db. You can have more informations
     * about the crossroad doing a GET request
     * on the ressource crossroad/[CrossRoad name]
     *
     * @return a Response containing all the crossroads names
     */
    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<String> retrieveCrossRoads() {
        List<String> crossRoads = new ArrayList<>();
        crossRoads.add("carrefour_du_casino");
        crossRoads.add("carrefour_des_pins");
        return crossRoads;
    }

    @RequestMapping(value="/{crossRoadName}", method=RequestMethod.GET)
    public @ResponseBody CrossRoad retrieveSpecifcCrossRoad(@PathVariable String crossRoadName) {
        CrossRoad crossRoad = new CrossRoad("carrefour_du_casino", "url");
        crossRoad.addRoad("avenue du tapis vert");
        crossRoad.addRoad("avenue des orangers");

        TrafficLight trafficLight1 = new TrafficLight("feu de l'avenue des orangers");
        trafficLight1.addRoad("avenue des orangers");
        TrafficLight trafficLight2 = new TrafficLight("feu de l'avenue du tapis vert");
        trafficLight1.addRoad("avenue du tapis vert");

        crossRoad.addTrafficLight(trafficLight1);
        crossRoad.addTrafficLight(trafficLight2);

        Scenario scenar = new Scenario("basicScenario");
        RuleGroup group1 = new RuleGroup("group1", 20);
        RuleGroup group2 = new RuleGroup("group2", 40);
        TrafficLightId id1 = new TrafficLightId("feu de l'avenue des orangers");
        TrafficLightId id2 = new TrafficLightId("feu de l'avenue du tapis vert");
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id2);
        group2.addTrafficLight(id2);

        scenar.addRuleGroup(0, group1);
        scenar.addRuleGroup(1, group2);
        scenar.setTransitionTime(5);

        crossRoad.setScenario(scenar);
        return crossRoad;
    }

    @RequestMapping(value="/", method=RequestMethod.PUT)
    public Scenario receiveScenario(@RequestParam Scenario scenario) {

        // Puts the request to the crossroad
       // crossroadRequester.put("INRIA", "/starter", scenario);
        return scenario;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CrossroadService.class, args);
    }
}
