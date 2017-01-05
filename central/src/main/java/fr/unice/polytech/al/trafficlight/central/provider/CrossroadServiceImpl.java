package fr.unice.polytech.al.trafficlight.central.provider;


import fr.unice.polytech.al.trafficlight.central.business.CrossroadRetriever;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
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
    @Autowired
    private CrossroadRetriever crossroadRetriever;

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
        List<String> crossRoads = new ArrayList<>(crossroadRetriever.getAllCrossroadName());
        return crossRoads;
    }

    /**
     * Retrieves the crossroad corresponding to crossRoadName name
     * @param crossRoadName The name of the crossroad we want to retreive
     * @return a CrossRoad object or null if crossRoadName is not linked with a CrossRoad in the db
     */
    @RequestMapping(value="/{crossRoadName}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody CrossRoad retrieveSpecificCrossRoad(@PathVariable String crossRoadName) {
        return crossroadRetriever.getCrossroad(crossRoadName);
    }

    @RequestMapping(value="", method= RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String receiveCrossroad(@RequestBody CrossRoad crossroad) {

        crossroadRetriever.addCrossroad(crossroad);
        return "OK";

    }

    /**
     * Retrieves the scenario corresponding to crossRoadName name
     * @param crossRoadName The name of the crossroad we want to get it's scenario
     * @return a Scenario object or null if crossRoadName is not linked with a CrossRoad in the db
     */
    @RequestMapping(value="/{crossRoadName}/scenario", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Scenario retrieveScenarioCrossroad(@PathVariable String crossRoadName) {
        return crossroadRetriever.getCrossroad(crossRoadName).getScenario();
    }
}
