package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhoo on 16/11/16.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/scenario")
public class ScenarioServiceImpl implements ScenarioService {

    //@Autowired
    //private ScenarioCheckerImpl scenarioChecker;

    //@Autowired
    //private ScenarioRetreiverImpl scenarioRetreiver;

    /**
     * Retrieves all the existing scenario
     * in the db. You can have more informations
     * about the scenario doing a GET request
     * on the ressource scenario/[Scenario name]
     *
     * @return a Response containing all the scenario id
     */
    @RequestMapping(value="", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<String> retrieveCrossRoads() {
        //List<String> scenario = new ArrayList<>(scenarioRetreiver.getAllScenarioId());
        //return scenario;
        return null;
    }


    /**
     * Retrieves the crossroad corresponding to crossRoadName name
     * @param scenarioId The name of the scenario we want to retreive
     * @return a Scenario object or null if scenarioId is not linked with a scenario in the db
     */
    @RequestMapping(value="/{crossRoadName}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Scenario retrieveSpecificCrossRoad(@PathVariable String scenarioId) {

        return null;
        //return scenarioRetreiver.getScenario(scenarioId);
    }

    @RequestMapping(value="", method= RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String receiveScenario(@RequestBody Scenario scenario) {

        return null;
        //return scenarioChecker.checkScenario(scenario);

    }
}
