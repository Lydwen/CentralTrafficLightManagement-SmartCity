package fr.unice.polytech.al.trafficlight.central.database;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoadGraphLinker;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */
public class CrossRoadStock {

    private CrossRoadGraphLinker crossroadLinker = new CrossRoadGraphLinker();

    public void add(GeolocalizedCrossroad crossroad) {
        crossroadLinker.getCrossRoadData().put(crossroad.getName(), crossroad);
    }

    public GeolocalizedCrossroad get(String name) {
        return crossroadLinker.getCrossRoadData().containsKey(name) ? crossroadLinker.getCrossRoadData().get(name) : null;
    }

    public void remove(String name) {
        crossroadLinker.getCrossRoadData().remove(name);
    }

    public Set<String> getAllKey() {
        return crossroadLinker.getCrossRoadData().keySet();
    }

    public WeightedDirectedGraph<GeolocalizedCrossroad> getGraph(){
        return crossroadLinker.getGraph();
    }

    public Set<GeolocalizedCrossroad> getAllScenario() {
        return new HashSet<>(crossroadLinker.getCrossRoadData().values());
    }

    public void clearDatabase() {
        crossroadLinker.getCrossRoadData().clear();
    }
}
