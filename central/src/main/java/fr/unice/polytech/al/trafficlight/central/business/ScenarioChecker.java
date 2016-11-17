package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;

/**
 * Created by rhoo on 14/11/16.
 */
public interface ScenarioChecker {

    String checkScenario(Scenario scenario);

    String checkAndSetScenario(Scenario scenario, CrossRoad crossRoad);

    String checkAndSetScenario(Scenario scenario, String crossRoadName);

    }
