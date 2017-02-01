package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.business.GraphRetriever;
import fr.unice.polytech.al.trafficlight.graph.WeightedDirectedGraph;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

/**
 * Provide a rest interface to retrieve
 * a lightweight version of the crossroad graph
 *
 * Created by tom on 30/01/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value="/graph")
public class CrossroadGraphRetrieverImpl implements CrossroadGraphRetriever {
    @Autowired
    GraphRetriever retriever;

    @RequestMapping(value="", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @Override
    public WeightedDirectedGraph<CrossRoadCore> retrieveCrossRoadGraph() {
        WeightedDirectedGraph<CrossRoadCore> graph = retriever.retrieveGraph();

        return graph;
    }
}
