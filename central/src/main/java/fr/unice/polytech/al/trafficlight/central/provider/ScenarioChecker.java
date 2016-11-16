package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;

/**
 * Created by rhoo on 14/11/16.
 */
public interface ScenarioChecker {

    public String checkScenario(Scenario scenario, String crossRoad);
}
