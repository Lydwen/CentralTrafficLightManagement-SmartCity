package fr.unice.polytech.al.trafficlight.route.provider;

import fr.unice.polytech.al.trafficlight.route.database.CrossRoadStock;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.PremiumCarLocation;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

/**
 * Premium car service.
 *
 * @author Kevin Buisson
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/premium")
public class PremiumCarServiceImpl implements PremiumCarService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger.getLogger(PremiumCarServiceImpl.class);

    /**
     * Crossroad database.
     */
    private CrossRoadStock crossRoadStock = new CrossRoadStock();

    @Override
    @RequestMapping(value = "/location", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String declareLocation(@RequestBody PremiumCarLocation premiumCarLocation) {
        CrossRoad crossRoad;
        TrafficLight trafficLight;

        System.out.println(premiumCarLocation);
        // Get crossroad and traffic light
        try {
            String crossroadName = premiumCarLocation.getCrossroadId().getId();
            String trafficLightName = premiumCarLocation.getTrafficLightId().getId();

            // Get crossroad
            crossRoad = Optional.ofNullable(crossRoadStock.get(crossroadName))
                    // Handle if crossroad does not exists
                    .orElseThrow(() -> new Exception(String.format(
                            "Crossroad '%s' does not exists.", crossroadName)));

            // Get traffic light in the crossroad
            trafficLight = crossRoad.getTrafficLights().stream().filter(tl -> tl.getName().equals(trafficLightName)).findFirst()
                    // Handle if traffic light does not exists
                    .orElseThrow(() -> new Exception(String.format(
                            "Traffic light '%s' does not exists in crossroad '%s'.", trafficLightName, crossroadName)));
        } catch (Exception e) {
            // Log error
            logger.error(e.getMessage());
            return String.format("{\"status\": \"KO\", \"reason\": \"%s\"}", e.getMessage());
        }

        // Log success
        logger.info(premiumCarLocation); // TODO Something else than just log success
        String urlString = ""; // TODO put the good carefourid in this
        //urlString += "/trafficlight/" + trafficlightId + "/vehicle/" + vehicleID // TODO uncomment and edit variable name
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("");
            out.close();
            httpCon.getInputStream();
        }
        catch (Exception e) {
            // Log error
            logger.error(e.getMessage());
            return String.format("{\"status\": \"KO\", \"reason\": \"%s\"}", e.getMessage());
        }

        return "{\"status\": \"OK\"}";
    }
}
