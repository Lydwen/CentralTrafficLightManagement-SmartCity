package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by rhoo on 16/11/16.
 */
public interface ScenarioService {

    public List<String> retrieveScenario();

    public @ResponseBody Scenario retrieveSpecificScenario(@PathVariable String scenarioId);

    public String receiveScenario(@RequestBody Scenario scenario);
}
