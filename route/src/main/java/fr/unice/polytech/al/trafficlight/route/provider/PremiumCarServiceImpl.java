package fr.unice.polytech.al.trafficlight.route.provider;

import fr.unice.polytech.al.trafficlight.route.database.CrossRoadStock;
import fr.unice.polytech.al.trafficlight.route.utils.WebRequester;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
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

    /**
     * Crossroad requester.
     */
    @Autowired
    @Qualifier("crossroadsRequester")
    private WebRequester crossroadsRequester;


    @Override
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(
            value = "/crossroad/{crossroadName}/trafficlight/{trafficlightName}/vehicle/{vehicleId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
    public String declareLocation(@PathVariable String crossroadName,
                                  @PathVariable String trafficlightName,
                                  @PathVariable String vehicleId) {
        CrossRoad crossRoad;
        TrafficLight trafficLight;

        // Get crossroad and traffic light
        try {
            // Check traffic light
            trafficLight = checkTrafficlight(trafficlightName,
                    // Check crossroad
                    crossRoad = checkCrossroad(crossroadName));

            // Log the request // TODO Something else than just log success
            logger.info("[PREMIUM] Car " + vehicleId + " ARRIVING on " + trafficLight.getName() + " of " + crossRoad.getName());

            // Transfer the request to the crossroad
            crossroadsRequester.post(crossRoad.getName(),
                    String.format("/crossroad/trafficlight/%s/vehicle/%s", trafficLight.getName(), vehicleId),
                    "");

        } catch (Exception e) {
            return handleError(e);
        }

        return handleSuccess();
    }

    @Override
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(
            value = "/crossroad/{crossroadName}/trafficlight/{trafficlightName}/vehicle/{vehicleId}",
            method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
    public String removeLocation(@PathVariable String crossroadName,
                                 @PathVariable String trafficlightName,
                                 @PathVariable String vehicleId) {
        CrossRoad crossRoad;
        TrafficLight trafficLight;

        // Get crossroad and traffic light
        try {
            // Check traffic light
            trafficLight = checkTrafficlight(trafficlightName,
                    // Check crossroad
                    crossRoad = checkCrossroad(crossroadName));

            // Log the request // TODO Something else than just log success
            logger.info("[PREMIUM] Car " + vehicleId + " LEAVING " + trafficLight.getName() + " of " + crossRoad.getName());

            // Transfer the request to the crossroad
            crossroadsRequester.delete(crossRoad.getName(),
                    String.format("/crossroad/trafficlight/%s/vehicle/%s", trafficLight.getName(), vehicleId));

        } catch (Exception e) {
            return handleError(e);
        }

        return handleSuccess();
    }

    // ==================================================================

    /**
     * Check if crossroad exists.
     *
     * @param crossroadName crossroad's name
     * @return crossroad
     * @throws RuntimeException if crossroad does not exists
     */
    private CrossRoad checkCrossroad(String crossroadName) {
        // Get crossroad
        return Optional.ofNullable(crossRoadStock.get(crossroadName))
                // Handle if crossroad does not exists
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Crossroad '%s' does not exists.", crossroadName)));
    }

    /**
     * Check if traffic light exists.
     *
     * @param trafficlightName traffic light's name
     * @return traffic light
     * @throws RuntimeException if traffic light does not exists
     */
    private TrafficLight checkTrafficlight(String trafficlightName, CrossRoad crossRoad) {
        return crossRoad.getTrafficLights().stream().filter(tl -> tl.getName().equals(trafficlightName)).findFirst()
                // Handle if traffic light does not exists
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Traffic light '%s' does not exists in crossroad '%s'.", trafficlightName, crossRoad.getName())));
    }

    /**
     * Success.
     *
     * @return message
     */
    private String handleSuccess() {
        // OK status
        return "{\"status\": \"OK\"}";
    }

    /**
     * Error.
     *
     * @param e exception
     * @return message
     */
    private String handleError(Exception e) {
        // Log error
        logger.error(
                String.format("[PREMIUM] %s - %s", e.getClass().getName(), e.getMessage()));

        // KO status
        return String.format("{\"status\": \"KO\", \"reason\": \"%s\"}", e.getMessage());
    }

    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
