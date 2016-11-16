package fr.unice.polytech.al.trafficlight.central.provider;


import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.central.provider.utils.WebRequester;
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
    @Autowired
    private WebRequester crossroadRequester;
    @Autowired
    private CrossroadRetriever crossroadRetreiver;

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
        List<String> crossRoads = new ArrayList<>(crossroadRetreiver.getAllCrossroadName());
        return crossRoads;
    }

    /**
     * Retrieves the crossroad corresponding to crossRoadName name
     * @param crossRoadName The name of the crossroad we want to retreive
     * @return a CrossRoad object or null if crossRoadName is not linked with a CrossRoad in the db
     */
    @RequestMapping(value="/{crossRoadName}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody CrossRoad retrieveSpecificCrossRoad(@PathVariable String crossRoadName) {
        return crossroadRetreiver.getCrossroad(crossRoadName);
    }

    @RequestMapping(value="", method= RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String receiveCrossroad(@RequestBody CrossRoad crossroad) {

        crossroadRetreiver.addCrossroad(crossroad);
        return "OK";

    }
}
