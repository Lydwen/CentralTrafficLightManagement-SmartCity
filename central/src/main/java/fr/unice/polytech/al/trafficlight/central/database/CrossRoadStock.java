package fr.unice.polytech.al.trafficlight.central.database;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.central.data.TrafficLight;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;

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
        CrossRoad crossRoad = new CrossRoad("carrefour_du_casino", "url");
        crossRoad.addRoad("avenue du tapis vert");
        crossRoad.addRoad("avenue des orangers");

        TrafficLight trafficLight1 = new TrafficLight("feu de l'avenue des orangers");
        trafficLight1.addRoad("avenue des orangers");
        TrafficLight trafficLight2 = new TrafficLight("feu de l'avenue du tapis vert");
        trafficLight1.addRoad("avenue du tapis vert");

        crossRoad.addTrafficLight(trafficLight1);
        crossRoad.addTrafficLight(trafficLight2);

        Scenario scenar = new Scenario("basicScenario");
        RuleGroup group1 = new RuleGroup("group1", 20);
        RuleGroup group2 = new RuleGroup("group2", 40);
        TrafficLightId id1 = new TrafficLightId("feu de l'avenue des orangers");
        TrafficLightId id2 = new TrafficLightId("feu de l'avenue du tapis vert");
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id2);
        group2.addTrafficLight(id2);

        scenar.addRuleGroup(0, group1);
        scenar.addRuleGroup(1, group2);
        scenar.setTransitionTime(5);

        crossRoad.setScenario(scenar);

        crossroadData.put(crossRoad.getName(),crossRoad);

        crossRoad = new CrossRoad("carrefour_de_l_INRIA", "url");
        crossRoad.addRoad("avenue de polytech");
        crossRoad.addRoad("avenue de l'IUT");
        crossRoad.addRoad("avenue des lucioles");
        crossRoad.addRoad("avenue des murs");

        trafficLight1 = new TrafficLight("north");
        trafficLight1.addRoad("avenue de polytech");
        trafficLight2 = new TrafficLight("west");
        trafficLight1.addRoad("avenue de l'IUT");
        trafficLight1 = new TrafficLight("east");
        trafficLight1.addRoad("avenue des lucioles");
        trafficLight2 = new TrafficLight("south");
        trafficLight1.addRoad("avenue des murs");

        crossRoad.addTrafficLight(trafficLight1);
        crossRoad.addTrafficLight(trafficLight2);

        scenar = new Scenario("basicScenario");
        group1 = new RuleGroup("group1", 10);
        group2 = new RuleGroup("group2", 30);
        id1 = new TrafficLightId("north");
        id2 = new TrafficLightId("west");
        TrafficLightId id3 = new TrafficLightId("east");
        TrafficLightId id4 = new TrafficLightId("south");
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id2);
        group2.addTrafficLight(id3);
        group2.addTrafficLight(id4);

        scenar.addRuleGroup(0, group1);
        scenar.addRuleGroup(1, group2);
        scenar.setTransitionTime(5);

        crossRoad.setScenario(scenar);

        crossroadData.put(crossRoad.getName(),crossRoad);
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
