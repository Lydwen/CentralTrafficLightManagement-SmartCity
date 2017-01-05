package fr.unice.polytech.al.trafficlight.route.database;

import fr.unice.polytech.al.trafficlight.fakedata.CrossroadsFakeData;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Crossroad database.
 */
public class CrossRoadStock {

    private Map<String, CrossRoad> crossroadData = CrossroadsFakeData.get();

    public void add(CrossRoad crossroad) {
        crossroadData.put(crossroad.getName(), crossroad);
    }

    public CrossRoad get(String name) {
        return crossroadData.containsKey(name) ? crossroadData.get(name) : null;
    }

    public void remove(String name) {
        crossroadData.remove(name);
    }

    public Set<String> getAllKey() {
        return crossroadData.keySet();
    }

    public Set<CrossRoad> getAllScenario() {
        return new HashSet<>(crossroadData.values());
    }

    public void clearDatabase() {
        crossroadData.clear();
    }
}
