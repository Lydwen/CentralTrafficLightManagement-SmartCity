package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;

import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tom on 30/01/17.
 */
public interface CrossroadGraphRetriever {

    @ResponseBody
    WeightedDirectedGraph<CrossRoadCore> retrieveCrossRoadGraph();

}
