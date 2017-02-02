package fr.unice.polytech.al.trafficlight.route.provider;

import fr.unice.polytech.al.trafficlight.route.utils.CrossRoadListWrapper;
import fr.unice.polytech.al.trafficlight.route.utils.CrossroadPropertiesExtractor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

/**
 * Created by tom on 02/02/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/")
public class CrossroadRetrieverImpl implements CrossroadRetriever{
    @Override
    @RequestMapping(
            value = "crossroadList",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public CrossRoadListWrapper getAllCrossroads() {
        CrossroadPropertiesExtractor propertiesExtractor = new CrossroadPropertiesExtractor();

        return propertiesExtractor.getWrapper();
    }
}
