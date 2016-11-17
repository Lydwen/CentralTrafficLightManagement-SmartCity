package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.business.CrossroadRetriever;
import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.central.utils.WebRequester;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Emergency service implementation.
 *
 * @author Kévin Buisson
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/emergency")
public class EmergencyServiceImpl implements EmergencyService {
    @Autowired
    private CrossroadRetriever crossroadRetriever;

    @Autowired
    @Qualifier("crossroadsRequester")
    private WebRequester crossroadsRequester;

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
    public void declareEmergency(@RequestBody Emergency emergency, HttpServletResponse response) throws IOException {
        // Check crossroad exists
        CrossRoad crossRoad = crossroadRetriever.getCrossroad(emergency.getCrossroadId().getId());
        if (crossRoad == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Error: The crossroad does not exists.");
            return;
        }

        // Send the emergency
        try {
            crossroadsRequester.put(crossRoad.getName(), "/crossroad/emergency", emergency);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (RestClientException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
