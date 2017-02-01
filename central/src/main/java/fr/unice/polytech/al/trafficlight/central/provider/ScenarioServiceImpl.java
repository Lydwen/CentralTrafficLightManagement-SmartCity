package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.business.ScenarioChecker;
import fr.unice.polytech.al.trafficlight.central.business.ScenarioRetriever;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by rhoo on 16/11/16.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/scenario")
public class ScenarioServiceImpl implements ScenarioService {

    @Autowired
    private ScenarioChecker scenarioChecker;

    @Autowired
    private ScenarioRetriever scenarioRetriever;

    /**
     * Retrieves all the existing scenario
     * in the db. You can have more informations
     * about the scenario doing a GET request
     * on the ressource scenario/[Scenario name]
     *
     * @return a Response containing all the scenario id
     */
    @RequestMapping(value="", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Wrapper retrieveScenario() {
        Wrapper wrap = new Wrapper();
        wrap.setCrossRoadName(new ArrayList<>(scenarioRetriever.getAllScenarioId()));
        return wrap;
    }


    /**
     * Retrieves the crossroad corresponding to crossRoadName name
     * @param scenario The name of the scenario we want to retreive
     * @return a Scenario object or null if scenarioId is not linked with a scenario in the db
     */
    @RequestMapping(value="/{scenario}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Scenario retrieveSpecificScenario(@PathVariable String scenario) {

        return scenarioRetriever.getScenario(scenario);
    }

    @RequestMapping(value="/{idCrossRoad}", method= RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String receiveScenario(@RequestBody Scenario scenario, @PathVariable String idCrossRoad) {
        return scenarioChecker.checkAndSetScenario(scenario, idCrossRoad);
    }

    @RequestMapping(value="/{idCrossRoad}", method= RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String receiveScenarioWithSpread(@RequestBody Scenario scenario, @PathVariable String idCrossRoad, @RequestBody String spread) {
        return scenarioChecker.checkAndSetScenario(scenario, idCrossRoad, spread);

    }
}
