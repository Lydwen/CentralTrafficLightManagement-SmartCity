package fr.unice.polytech.al.trafficlight.central.database;

import fr.unice.polytech.al.trafficlight.utils.Scenario;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */

public class ScenarioStock {

    private Map<String,Scenario> scenarioData;

    public ScenarioStock() {
        scenarioData = new HashMap<>();
    }

    public void add(Scenario scenario) {
        scenarioData.put(scenario.getId(),scenario);
    }

    public Scenario get(String id) {
        return scenarioData.containsKey(id)?scenarioData.get(id):null;
    }

    public void remove(String id) {
        scenarioData.remove(id);
    }

    public Set<String> getAllKey() {
        return scenarioData.keySet();
    }

    public Set<Scenario> getAllScenario() {
        return new HashSet<>(scenarioData.values());
    }
}
