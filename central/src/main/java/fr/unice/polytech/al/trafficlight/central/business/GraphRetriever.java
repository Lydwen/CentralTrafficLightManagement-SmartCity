package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;

/**
 * Created by tom on 30/01/17.
 */
public interface GraphRetriever {
    WeightedDirectedGraph<CrossRoadCore> retrieveGraph();
}
