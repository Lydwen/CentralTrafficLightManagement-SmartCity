package fr.unice.polytech.al.trafficlight.central.database;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoadGraphLinker;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.graph.Edge;
import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public List<Edge<GeolocalizedCrossroad>> getEdgesFromCrossroadOnRoad(GeolocalizedCrossroad geoCrossroad, String road){
        Set<Edge<GeolocalizedCrossroad>> crossroads = new HashSet<>();
        Set<Edge<GeolocalizedCrossroad>> crossroadsEnter = new HashSet<>();
        Set<Edge<GeolocalizedCrossroad>> crossroadsExit = new HashSet<>();

        crossroadsEnter.addAll(crossroadLinker.getEdgesFromCrossRoadWithRoad(geoCrossroad, road));

        //while we have new crossroads we try to find new linked crossroad that goes from the end of the edge to another crossroad
        for(Edge<GeolocalizedCrossroad> edge : crossroadsEnter){
            //we add only new crossroad
            crossroadsEnter.addAll(crossroadLinker.getEdgesFromCrossRoadWithRoad(edge.getEnd(), road));
        }

        crossroadsExit.addAll(crossroadLinker.getEdgesToCrossRoadWithRoad(geoCrossroad, road));
        //while we have new crossroads we try to find new linked crossroad that goes to the begin of a edge
        for(Edge<GeolocalizedCrossroad> edge : crossroadsExit){
            //we add only new crossroad
            crossroadsExit.addAll(crossroadLinker.getEdgesToCrossRoadWithRoad(edge.getBegin(), road));
        }
        List<Edge<GeolocalizedCrossroad>> crossRoadsExitAsList = new ArrayList<>(crossroadsExit);
        List<Edge<GeolocalizedCrossroad>> crossRoadsExitAsListInvert = new ArrayList<>();

        //invert the list for convenience and invert the weight to a negative form
        for(int i = crossRoadsExitAsList.size()-1; i > 0; i--){
            Edge<GeolocalizedCrossroad> edge = crossRoadsExitAsList.get(i);
            Edge<GeolocalizedCrossroad> newEdge = new Edge<>(edge.getName(), edge.getBegin(), edge.getEnd(), -edge.getWeight());
            crossRoadsExitAsListInvert.add(newEdge);
        }

        crossroads.addAll(crossroadsEnter);
        crossroads.addAll(crossRoadsExitAsListInvert);
        return new ArrayList<>(crossroads);
    }

    public void clearDatabase() {
        crossroadLinker.getCrossRoadData().clear();
    }
}
