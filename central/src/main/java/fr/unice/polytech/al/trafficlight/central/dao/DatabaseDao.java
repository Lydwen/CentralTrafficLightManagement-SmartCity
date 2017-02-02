package fr.unice.polytech.al.trafficlight.central.dao;

import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.central.database.CrossRoadStock;
import fr.unice.polytech.al.trafficlight.central.database.ScenarioStock;
import fr.unice.polytech.al.trafficlight.graph.Edge;
import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    public void addCrossroad(GeolocalizedCrossroad crossroad) {
        crossRoadData.add(crossroad);
    }

    public GeolocalizedCrossroad getCrossroad(String name) {
        return crossRoadData.get(name);
    }

    public void removeCrossroad(String name) {
        crossRoadData.remove(name);
    }

    public Set<String> getAllCrossroadKey() {
        return crossRoadData.getAllKey();
    }

    public Set<GeolocalizedCrossroad> getAllCrossroad() {
        return crossRoadData.getAllScenario();
    }

    public WeightedDirectedGraph<GeolocalizedCrossroad> getGraph(){
        return crossRoadData.getGraph();
    }

    public List<Edge<GeolocalizedCrossroad>>  getAllCrossroadLinkedWithRoadFrom(String crossroad, String road){
        List<Edge<GeolocalizedCrossroad>> crossroads = new ArrayList<>();
        GeolocalizedCrossroad geoCrossroad= getCrossroad(crossroad);
        if(geoCrossroad != null){
            crossroads = crossRoadData.getEdgesFromCrossroadOnRoad(geoCrossroad, road);
        }

        return crossroads;
    }

    public void clearDatabase() {
        crossRoadData.clearDatabase();
        scenarioData.clearDatabase();
    }
}
