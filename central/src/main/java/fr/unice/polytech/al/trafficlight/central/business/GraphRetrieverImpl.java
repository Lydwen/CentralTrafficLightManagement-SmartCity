package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.central.utils.CrossRoadConverter;
import fr.unice.polytech.al.trafficlight.graph.Edge;
import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 30/01/17.
 */
@Service
public class GraphRetrieverImpl implements GraphRetriever{
    @Autowired
    DatabaseDao db;
    private CrossRoadConverter crossRoadConverter = new CrossRoadConverter();

    /**
     * Retrieve a lightweight version of the graph
     *
     * @return a graph with all the crossroad and their links
     */
    @Override
    public WeightedDirectedGraph<CrossRoadCore> retrieveGraph() {
        //retrieve the graph from the db
        WeightedDirectedGraph<GeolocalizedCrossroad> graphGeo = db.getGraph();
        Map<GeolocalizedCrossroad, List<Edge<GeolocalizedCrossroad>>> graph = graphGeo.getOutGraph();

        WeightedDirectedGraph<CrossRoadCore> graphCore = new WeightedDirectedGraph<>();
        //create the dictionnary that links a geo crossroad to its core version
        Map<GeolocalizedCrossroad, CrossRoadCore> geoToCore = new HashMap<>();

        //parse each node of the graph
        for(GeolocalizedCrossroad cross : graph.keySet()){
            CrossRoadCore begin = retrieveCoreFromGeo(cross, geoToCore);

            //add the edge in the lightweight version of the graph
            for(Edge<GeolocalizedCrossroad> edge : graph.get(cross)){
                CrossRoadCore end = retrieveCoreFromGeo(edge.getEnd(), geoToCore);
                graphCore.addEdge(edge.getName(), begin, end, edge.getWeight());
            }
        }

        return graphCore;
    }

    private CrossRoadCore retrieveCoreFromGeo(GeolocalizedCrossroad geoCrossRoad, Map<GeolocalizedCrossroad, CrossRoadCore> geoToCore){
        if(geoToCore.containsKey(geoCrossRoad)){
            return geoToCore.get(geoCrossRoad);
        }

        CrossRoadCore core = crossRoadConverter.geolocalizedCrossroadToCrossRoadCore(geoCrossRoad);
        geoToCore.put(geoCrossRoad, core);

        return core;
    }
}
