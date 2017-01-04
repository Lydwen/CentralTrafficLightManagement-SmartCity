package fr.unice.polytech.al.trafficlight.central.dao;

import fr.unice.polytech.al.trafficlight.central.database.CrossRoadStock;
import fr.unice.polytech.al.trafficlight.central.database.ScenarioStock;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */
@Repository
public class DatabaseDao {

    private ScenarioStock scenarioData = new ScenarioStock();
    private CrossRoadStock crossRoadData = new CrossRoadStock();

    public void addScenario(Scenario scenario) {
        scenarioData.add(scenario);
    }

    public Scenario getScenario(String id) {
        return scenarioData.get(id);
    }

    public void removeScenario(String id) {
        scenarioData.remove(id);
    }

    public Set<String> getAllScenarioKey() {
        return scenarioData.getAllKey();
    }

    public Set<Scenario> getAllScenario() {
        return scenarioData.getAllScenario();
    }

    public void addCrossroad(CrossRoad crossroad) {
        crossRoadData.add(crossroad);
    }

    public CrossRoad getCrossroad(String name) {
        return crossRoadData.get(name);
    }

    public void removeCrossroad(String name) {
        crossRoadData.remove(name);
    }

    public Set<String> getAllCrossroadKey() {
        return crossRoadData.getAllKey();
    }

    public Set<CrossRoad> getAllCrossroad() {
        return crossRoadData.getAllScenario();
    }

    public void clearDatabase() {
        crossRoadData.clearDatabase();
        scenarioData.clearDatabase();
    }
}
