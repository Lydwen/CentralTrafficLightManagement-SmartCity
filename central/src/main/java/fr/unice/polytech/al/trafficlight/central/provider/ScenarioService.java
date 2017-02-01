package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.Wrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by rhoo on 16/11/16.
 */
public interface ScenarioService {

    public Wrapper retrieveScenario();

    public
    @ResponseBody
    Scenario retrieveSpecificScenario(@PathVariable String scenarioId);

    public String receiveScenario(@RequestBody Scenario scenario, @PathVariable String idCrossRoad);

    public String receiveScenarioWithSpread(@RequestBody Scenario scenario, @PathVariable String idCrossRoad, @PathVariable String road);
}
