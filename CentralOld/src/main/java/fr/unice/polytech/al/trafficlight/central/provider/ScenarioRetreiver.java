package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.utils.Scenario;

import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */
public interface ScenarioRetreiver {

    public Scenario getScenario(String id);

    public Set<Scenario> getAllScenario();

    public Set<String> getAllScenarioId();

    public Set<String> getScenarioIdMatchTo(String searchId);
}
