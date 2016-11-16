package fr.unice.polytech.al.trafficlight.central.Database;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */
public class CrossRoadStock {

    private Map<String,CrossRoad> crossroadData;

    public CrossRoadStock() {
        crossroadData = new HashMap<>();
    }

    public void add(CrossRoad crossroad) {
        crossroadData.put(crossroad.getName(),crossroad);
    }

    public CrossRoad get(String name) {
        return crossroadData.containsKey(name)?crossroadData.get(name):null;
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
}
