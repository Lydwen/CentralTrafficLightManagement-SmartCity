package fr.unice.polytech.al.trafficlight.central.data.mock;

import fr.unice.polytech.al.trafficlight.utils.Coordinates;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.utils.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 29/01/17.
 */
public class FakeGeolocalizedCrossRoad {
    /**
     * Gets crossroads data.
     *
     * @return crossroads data
     */
    public static Map<String, GeolocalizedCrossroad> get() {
        Map<String, GeolocalizedCrossroad> crossroadData = new HashMap<>();

        // =====================================================
        GeolocalizedCrossroad crossRoad = getCasino();
        crossroadData.put(crossRoad.getName(), crossRoad);

        //add a scenario with 4 traffic lights
        crossRoad = getInria();
        crossroadData.put(crossRoad.getName(), crossRoad);

        return crossroadData;
    }

    public static WeightedDirectedGraph<GeolocalizedCrossroad> getGraph(){
        WeightedDirectedGraph<GeolocalizedCrossroad> graph = new WeightedDirectedGraph<>();

        GeolocalizedCrossroad inria = getInria();
        GeolocalizedCrossroad casino = getCasino();

        graph.addEdge("roadBetweenInriaCasino",inria, casino, 12);
        graph.addEdge("roadBetweenCasinoInria", inria, casino, 21);

        return graph;
    }

    private static GeolocalizedCrossroad getInria(){
        Scenario scenar = new Scenario("basicScenario");
        RuleGroup group1 = new RuleGroup("group1", 10, 10);
        RuleGroup group2 = new RuleGroup("group2", 30, 30);
        TrafficLightId id1 = new TrafficLightId("north");
        TrafficLightId id2 = new TrafficLightId("west");
        TrafficLightId id3 = new TrafficLightId("east");
        TrafficLightId id4 = new TrafficLightId("south");
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id4);
        group2.addTrafficLight(id2);
        group2.addTrafficLight(id3);

        scenar.addRuleGroup(0, group1);
        scenar.addRuleGroup(1, group2);
        scenar.setTransitionTime(5);

        GeolocalizedCrossroad crossRoad = new GeolocalizedCrossroad("carrefour_de_l_INRIA", "url", scenar, new Coordinates(43.617296, 7.074373));
        crossRoad.addRoad("avenue de polytech");
        crossRoad.addRoad("avenue de l'IUT");
        crossRoad.addRoad("avenue des lucioles");
        crossRoad.addRoad("avenue des murs");

        TrafficLight trafficLight1 = new TrafficLight("north", new Coordinates(43.617331, 7.074382));
        trafficLight1.addRoad("avenue de polytech");
        TrafficLight trafficLight2 = new TrafficLight("west", new Coordinates(43.617335, 7.074186));
        trafficLight2.addRoad("avenue de l'IUT");
        TrafficLight trafficLight3 = new TrafficLight("east", new Coordinates(43.617261, 7.074556));
        trafficLight3.addRoad("avenue des lucioles");
        TrafficLight trafficLight4 = new TrafficLight("south", new Coordinates(43.617455, 7.074443));
        trafficLight4.addRoad("avenue des murs");

        crossRoad.addTrafficLight(trafficLight1);
        crossRoad.addTrafficLight(trafficLight2);
        crossRoad.addTrafficLight(trafficLight3);
        crossRoad.addTrafficLight(trafficLight4);

        return crossRoad;
    }

    private static GeolocalizedCrossroad getCasino(){
        Scenario scenar = new Scenario("basicScenario");
        RuleGroup group1 = new RuleGroup("group1", 20, 20);
        RuleGroup group2 = new RuleGroup("group2", 40, 40);
        RuleGroup group3 = new RuleGroup("group3", 30, 30);
        RuleGroup group4 = new RuleGroup("group4", 20, 20);
        TrafficLightId id1 = new TrafficLightId("droite");
        TrafficLightId id2 = new TrafficLightId("bas");
        TrafficLightId id3 = new TrafficLightId("gauche");
        TrafficLightId id4 = new TrafficLightId("haut");
        TrafficLightId id5 = new TrafficLightId("haut_droite");
        TrafficLightId id6 = new TrafficLightId("bas_droite");
        TrafficLightId id7 = new TrafficLightId("bas_gauche");
        TrafficLightId id8 = new TrafficLightId("haut_gauche");
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id3);
        group2.addTrafficLight(id2);
        group2.addTrafficLight(id4);
        group3.addTrafficLight(id5);
        group3.addTrafficLight(id7);
        group4.addTrafficLight(id6);
        group4.addTrafficLight(id8);

        scenar.addRuleGroup(0, group1);
        scenar.addRuleGroup(1, group2);
        scenar.addRuleGroup(2, group3);
        scenar.addRuleGroup(3, group4);
        scenar.setTransitionTime(5);

        //first scenario with 8 traffic lights
        GeolocalizedCrossroad crossRoad = new GeolocalizedCrossroad("carrefour_du_casino", "url", scenar, new Coordinates(10.617531, 10.074482));
        crossRoad.addRoad("avenue du tapis vert");
        crossRoad.addRoad("avenue des orangers");
        crossRoad.addRoad("avenue des italiens");
        crossRoad.addRoad("avenue des rhododendrons");

        TrafficLight trafficLight1 = new TrafficLight("droite", new Coordinates(10.617531, 10.074482));
        trafficLight1.addRoad("avenue des orangers");
        TrafficLight trafficLight2 = new TrafficLight("haut_droite", new Coordinates(10.617431, 10.075382));
        trafficLight2.addRoad("avenue du tapis vert");
        TrafficLight trafficLight3 = new TrafficLight("haut", new Coordinates(10.617331, 10.074382));
        trafficLight3.addRoad("avenue des italiens");
        TrafficLight trafficLight4 = new TrafficLight("haut_gauche", new Coordinates(10.617381, 10.074482));
        trafficLight4.addRoad("avenue des rhododendrons");
        TrafficLight trafficLight5 = new TrafficLight("gauche", new Coordinates(10.617339, 10.074386));
        trafficLight5.addRoad("avenue des orangers");
        TrafficLight trafficLight6 = new TrafficLight("bas_gauche", new Coordinates(10.617231, 10.073382));
        trafficLight6.addRoad("avenue du tapis vert");
        TrafficLight trafficLight7 = new TrafficLight("bas", new Coordinates(10.617311, 10.074352));
        trafficLight7.addRoad("avenue des italiens");
        TrafficLight trafficLight8 = new TrafficLight("bas_droite", new Coordinates(10.617321, 10.074312));
        trafficLight8.addRoad("avenue des rhododendrons");

        crossRoad.addTrafficLight(trafficLight1);
        crossRoad.addTrafficLight(trafficLight2);
        crossRoad.addTrafficLight(trafficLight3);
        crossRoad.addTrafficLight(trafficLight4);
        crossRoad.addTrafficLight(trafficLight5);
        crossRoad.addTrafficLight(trafficLight6);
        crossRoad.addTrafficLight(trafficLight7);
        crossRoad.addTrafficLight(trafficLight8);

        return crossRoad;
    }


}
