package fr.unice.polytech.al.trafficlight.central.provider;


import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.central.provider.utils.WebRequesterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
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
public class CrossroadServiceImpl implements CrossroadService {

    /**
     * Crossroad web requester.
     */
    //@Autowired
    //private WebRequesterImpl crossroadRequester;
    //@Autowired
    //private CrossroadRetrieverImpl crossroadRetreiver;

    /**
     * Retrieves all the existing crossroads
     * in the db. You can have more informations
     * about the crossroad doing a GET request
     * on the ressource crossroad/[CrossRoad name]
     *
     * @return a Response containing all the crossroads names
     */
    @RequestMapping(value="", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<String> retrieveCrossRoads() {
        //List<String> crossRoads = new ArrayList<>(crossroadRetreiver.getAllCrossroadName());
        //return crossRoads;
        return null;
    }

    /**
     * Retrieves the crossroad corresponding to crossRoadName name
     * @param crossRoadName The name of the crossroad we want to retreive
     * @return a CrossRoad object or null if crossRoadName is not linked with a CrossRoad in the db
     */
    @RequestMapping(value="/{crossRoadName}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody CrossRoad retrieveSpecificCrossRoad(@PathVariable String crossRoadName) {
        /*CrossRoad crossRoad = new CrossRoad("carrefour_du_casino", "url");
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

        crossRoad.setScenario(scenar);*///TODO Je laisse ça là au cas où qu'on ai besoin d'init auto
        //return crossroadRetreiver.getCrossroad(crossRoadName);
        return null;
    }

    @RequestMapping(value="", method= RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String receiveCrossroad(@RequestBody CrossRoad crossroad) {

        //crossroadRetreiver.addCrossroad(crossroad);
        return "OK";

    }
}