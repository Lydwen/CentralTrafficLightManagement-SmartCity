package fr.unice.polytech.al.trafficlight.central.data;

import fr.unice.polytech.al.trafficlight.central.data.mock.FakeGeolocalizedCrossRoad;
import fr.unice.polytech.al.trafficlight.graph.Edge;
import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.fakedata.CrossroadsFakeData;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Gathering of the graph of the crossroad
 * with their corresponding name to retrieve them
 *
 * Created by tom on 27/01/17.
 */
public class CrossRoadGraphLinker {
    private WeightedDirectedGraph<GeolocalizedCrossroad> graph;
    private Map<String, GeolocalizedCrossroad> crossRoadData;

    public CrossRoadGraphLinker(){
        crossRoadData = FakeGeolocalizedCrossRoad.get();
        graph = FakeGeolocalizedCrossRoad.getGraph();
    }

    public WeightedDirectedGraph<GeolocalizedCrossroad> getGraph() {
        return graph;
    }

    public void setGraph(WeightedDirectedGraph<GeolocalizedCrossroad> graph) {
        this.graph = graph;
    }

    public Map<String, GeolocalizedCrossroad> getCrossRoadData() {
        return crossRoadData;
    }

    public void setCrossRoadData(Map<String, GeolocalizedCrossroad> crossRoadData) {
        this.crossRoadData = crossRoadData;
    }

    public Collection<Edge<GeolocalizedCrossroad>> getEdgesFromCrossRoadWithRoad(GeolocalizedCrossroad end, String road) {
        List<Edge<GeolocalizedCrossroad>> crossroads;

        crossroads = graph.getNeighboursByEdge(end, road);

        return crossroads;
    }

    public Collection<Edge<GeolocalizedCrossroad>> getEdgesToCrossRoadWithRoad(GeolocalizedCrossroad begin, String road) {
        List<Edge<GeolocalizedCrossroad>> crossroads;

        crossroads = graph.getNodesThatEntersInNodeFromEdge(begin, road);

        return crossroads;
    }
}
