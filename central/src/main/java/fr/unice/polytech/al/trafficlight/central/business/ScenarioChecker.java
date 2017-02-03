package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;

/**
 * Created by rhoo on 14/11/16.
 */
public interface ScenarioChecker {

    String checkScenario(Scenario scenario);

    String checkScenario(Scenario scenario, GeolocalizedCrossroad crossroad);

    String checkAndSetScenario(Scenario scenario, GeolocalizedCrossroad crossRoad);

    String checkAndSetScenario(Scenario scenario, String crossRoadName);

    String checkAndSetScenarioAndSpread(Scenario scenario, String idCrossRoad, String road);
}
